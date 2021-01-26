package dev.dommi.gameserver.backend.plugin.api.services.login;


import dev.dommi.gameserver.backend.adapter.api.ban.BanService;
import dev.dommi.gameserver.backend.adapter.api.login.InvalidLoginException;
import dev.dommi.gameserver.backend.adapter.api.login.LoginService;
import dev.dommi.gameserver.backend.adapter.api.user.User;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTSecretMissingException;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.openapi.annotations.*;

import java.util.logging.Logger;

public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    private final LoginService loginService;
    private final BanService banService;

    public LoginController(UserRepository userRepository, RankRepository rankRepository, BanRepository banRepository) {
        loginService = new LoginService(userRepository, rankRepository);
        banService = new BanService(banRepository, userRepository);
    }

    @OpenApi(
            summary = "Register user",
            operationId = "register",
            path = "/register",
            method = HttpMethod.POST,
            tags = {"Login"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = RegisterRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400", content = {@OpenApiContent(from = BadRequestResponse.class)})
            }
    )
    public void register(Context ctx) {
        RegisterRequest request = ctx.bodyAsClass(RegisterRequest.class);

        if (loginService.register(request.name, request.email, request.pw)) ctx.status(201);
        else ctx.status(400).json(new BadRequestResponse("Could not register User!"));

    }

    @OpenApi(
            summary = "Login user",
            operationId = "login",
            path = "/login",
            method = HttpMethod.POST,
            tags = {"Login"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = LoginRequest.class)}),
            responses = {
                    @OpenApiResponse(status = "201", content = {@OpenApiContent(from = LoginResponse.class)}),
                    @OpenApiResponse(status = "401", content = {@OpenApiContent(from = UnauthorizedResponse.class)}),
                    @OpenApiResponse(status = "500", content = {@OpenApiContent(from = InternalServerErrorResponse.class)})
            }
    )
    public void login(Context ctx) {
        LoginRequest request = ctx.bodyAsClass(LoginRequest.class);
        if (!banService.isUserBanned(request.email)) {
            try {
                User user = loginService.login(request.email, request.pw);
                ctx.status(201).json(new LoginResponse(JWTProvider.getInstance().generateToken(user)));

            } catch (InvalidLoginException e) {

                ctx.status(401).json(new UnauthorizedResponse(e.getMessage()));

            } catch (JWTSecretMissingException e) {
                logger.severe(e.getMessage());
                ctx.status(500).json(new InternalServerErrorResponse());
            }
        } else {
            ctx.status(401).json(new UnauthorizedResponse("You are banned!"));
        }
    }

}
