package dev.dommi.gameserver.backend.plugin.api.services.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dommi.gameserver.backend.adapter.api.user.User;
import dev.dommi.gameserver.backend.adapter.api.user.UserService;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.plugin.api.auth.AppRole;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTSecretMissingException;
import dev.dommi.gameserver.backend.plugin.api.server.APIServer;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.openapi.annotations.*;
import javalinjwt.JavalinJWT;

import java.util.Optional;
import java.util.logging.Logger;


public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private static final String NOT_FOUND_RESPONSE = "UserEntity not found";
    static final String USER_ID = "userId";
    private final UserService userService;

    public UserController(UserRepository userRepository) {
        userService = new UserService(userRepository);
    }

    @OpenApi(
            summary = "Get all users",
            operationId = "getAllUsers",
            path = "/users",
            method = HttpMethod.GET,
            tags = {"UserEntity"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User[].class)})
            }
    )
    public void getAll(Context ctx) {
        ctx.json(userService.getAll());
    }

    @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/users/:" + USER_ID,
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = USER_ID, type = Integer.class, description = "The user ID")},
            tags = {"UserEntity"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = NotFoundResponse.class)})
            }
    )
    public void getOne(Context ctx) {
        User user = userService.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse(NOT_FOUND_RESPONSE);
        } else {
            ctx.json(user);
        }
    }

    @OpenApi(
            summary = "Get user by LoginSession",
            operationId = "getUserBySession",
            path = "/user",
            method = HttpMethod.GET,
            tags = {"UserEntity"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "401", content = {@OpenApiContent(from = UnauthorizedResponse.class)}),
            }
    )
    public void getLoggedIn(Context ctx) {
        int userId = APIServer.getUserIDFromRequestToken(ctx);
        if (userId > -1) {
            User user = userService.findById(userId);
            if (user == null) {
                throw new NotFoundResponse(NOT_FOUND_RESPONSE);
            } else {
                ctx.json(user);
            }
        } else {
            throw new UnauthorizedResponse();
        }
    }

    @OpenApi(
            summary = "Update user by ID",
            operationId = "updateUserById",
            path = "/users/:" + USER_ID,
            method = HttpMethod.POST,
            pathParams = {@OpenApiParam(name = USER_ID, type = Integer.class, description = "The user ID")},
            tags = {"UserEntity"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = UserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "401", content = {@OpenApiContent(from = UnauthorizedResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = NotFoundResponse.class)})
            }
    )
    public void update(Context ctx) {

        if (validUserRequest(ctx)) {
            User user = userService.findById(validPathParamUserId(ctx));
            if (user == null) {
                throw new NotFoundResponse(NOT_FOUND_RESPONSE);
            } else {
                UserRequest newUser = ctx.bodyAsClass(UserRequest.class);
                if (userService.update(user.id, newUser.name, newUser.email)) {
                    ctx.status(204);
                } else {
                    throw new BadRequestResponse();
                }
            }
        } else {
            throw new UnauthorizedResponse();
        }


    }

    @OpenApi(
            summary = "Delete user by ID",
            operationId = "deleteUserById",
            path = "/users/:" + USER_ID,
            method = HttpMethod.DELETE,
            pathParams = {@OpenApiParam(name = USER_ID, type = Integer.class, description = "The user ID")},
            tags = {"UserEntity"},
            headers = {@OpenApiParam(name = "Authorization", required = true, description = "Example: 'Bearer <token>'")},
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "401", content = {@OpenApiContent(from = UnauthorizedResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = NotFoundResponse.class)})
            }
    )
    public void delete(Context ctx) {

        if (validUserRequest(ctx)) {
            User user = userService.findById(validPathParamUserId(ctx));
            if (user == null) {
                throw new NotFoundResponse(NOT_FOUND_RESPONSE);
            } else {
                userService.delete(user.id);
                ctx.status(204);
            }
        } else {
            throw new UnauthorizedResponse();
        }
    }

    // Prevent duplicate validation of userId
    static int validPathParamUserId(Context ctx) {
        return ctx.pathParam(USER_ID, Integer.class).check(id -> id > 0).get();
    }

    static boolean validUserRequest(Context ctx) {
        Optional<String> token = JavalinJWT.getTokenFromHeader(ctx);
        if (token.isPresent()) {
            try {
                DecodedJWT decodedJWT = JWTProvider.getInstance().verifyToken(token.get());
                int tokenID = decodedJWT.getClaims().get(JWTProvider.USER_ID).as(int.class);
                int tokenLevel = decodedJWT.getClaims().get(JWTProvider.USER_LEVEL).as(int.class);
                int requestID = validPathParamUserId(ctx);

                if (requestID == tokenID || tokenLevel >= AppRole.MODERATOR.level) {
                    return true;
                }
            } catch (JWTSecretMissingException e) {
                logger.severe(e.getMessage());
            }

        }
        return false;
    }

}
