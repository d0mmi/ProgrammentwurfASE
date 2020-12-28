package dev.dommi.gameserver.backend.plugin.api.services.user;

import dev.dommi.gameserver.backend.adapters.api.user.User;
import dev.dommi.gameserver.backend.adapters.api.user.UserService;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.plugin.openapi.annotations.*;


public class UserController {

    private static final String NOT_FOUND_RESPONSE = "UserEntity not found";
    private static  final UserService service = UserService.getInstance();

    @OpenApi(
            summary = "Create user",
            operationId = "createUser",
            path = "/users",
            method = HttpMethod.POST,
            tags = {"UserEntity"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public static void create(Context ctx) {
        NewUserRequest user = ctx.bodyAsClass(NewUserRequest.class);
        service.save(user.name, user.email);
        ctx.status(201);
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
    public static void getAll(Context ctx) {
        ctx.json(service.getAll());
    }

    @OpenApi(
            summary = "Get user by ID",
            operationId = "getUserById",
            path = "/users/:userId",
            method = HttpMethod.GET,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"UserEntity"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = User.class)}),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = NotFoundResponse.class)})
            }
    )
    public static void getOne(Context ctx) {
        User user = service.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse(NOT_FOUND_RESPONSE);
        } else {
            ctx.json(user);
        }
    }

    @OpenApi(
            summary = "Update user by ID",
            operationId = "updateUserById",
            path = "/users/:userId",
            method = HttpMethod.PATCH,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"UserEntity"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = NewUserRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = NotFoundResponse.class)})
            }
    )
    public static void update(Context ctx) {
        User user = service.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse(NOT_FOUND_RESPONSE);
        } else {
            NewUserRequest newUser = ctx.bodyAsClass(NewUserRequest.class);
            service.update(user.id, newUser.name, newUser.email);
            ctx.status(204);
        }
    }

    @OpenApi(
            summary = "Delete user by ID",
            operationId = "deleteUserById",
            path = "/users/:userId",
            method = HttpMethod.DELETE,
            pathParams = {@OpenApiParam(name = "userId", type = Integer.class, description = "The user ID")},
            tags = {"UserEntity"},
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)}),
                    @OpenApiResponse(status = "404", content = {@OpenApiContent(from = NotFoundResponse.class)})
            }
    )
    public static void delete(Context ctx) {
        User user = service.findById(validPathParamUserId(ctx));
        if (user == null) {
            throw new NotFoundResponse(NOT_FOUND_RESPONSE);
        } else {
            service.delete(user.id);
            ctx.status(204);
        }
    }

    // Prevent duplicate validation of userId
    private static int validPathParamUserId(Context ctx) {
        return ctx.pathParam("userId", Integer.class).check(id -> id > 0).get();
    }

}
