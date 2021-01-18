package dev.dommi.gameserver.backend.plugin.api.server;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dommi.gameserver.backend.plugin.api.auth.AppRole;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTSecretMissingException;
import dev.dommi.gameserver.backend.plugin.api.services.ban.BanController;
import dev.dommi.gameserver.backend.plugin.api.services.login.LoginController;
import dev.dommi.gameserver.backend.plugin.api.services.rank.RankController;
import dev.dommi.gameserver.backend.plugin.api.services.report.ReportController;
import dev.dommi.gameserver.backend.plugin.api.services.user.UserController;
import io.javalin.Javalin;
import io.javalin.core.security.Role;
import io.javalin.core.util.Header;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.ServiceUnavailableResponse;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import javalinjwt.JavalinJWT;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class APIServer {
    private static final Logger logger = Logger.getLogger(APIServer.class.getName());
    private int port;
    private Javalin server;

    private static final String API_PORT = "API_PORT";
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final String INVALID_AUTH_ERROR = "Missing, invalid token or not enough permissions";
    private static final String USERS_PATH = "users";
    private static final String USER_PATH = ":userId";
    private static final String REPORT_PATH = "report";
    private static final String REPORTS_PATH = "reports";
    private static final String REPORT_TYPES_PATH = "types";
    private static final String REPORTS_ID_PATH = ":reportId";
    private static final String BANS_PATH = "bans";
    private static final String BANS_ID_PATH = ":banId";
    private static final String LOGIN_PATH = "login";
    private static final String REGISTER_PATH = "register";
    private static final String ADMIN_PATH = "admin";
    private static final String RANKS_PATH = "ranks";
    private static final String GRANT_RANK_PATH = "grant";
    private static final String REVOKE_RANK_PATH = "revoke";
    private static final String API_VERSION = "1.0";
    private static final String API_DESCRIPTION = "UserEntity API";
    private static final String API_ANNOTATIONS_PACKAGE = "dev.dommi.gameserver.backend.plugin.api.services";
    private static final String SWAGER_DOCS_PATH = "/swagger-docs";
    private static final String SWAGER_UI_PATH = "/swagger-ui";
    private static final String REDOC_PATH = "/redoc";

    public APIServer() {
        this.port = Integer.parseInt(System.getenv(API_PORT));
        server = Javalin.create(config -> {
            config.registerPlugin(getConfiguredOpenApiPlugin());
            config.defaultContentType = DEFAULT_CONTENT_TYPE;
            config.accessManager((handler, ctx, permittedRoles) -> {
                if (permittedRoles.contains(AppRole.ANYONE)) handler.handle(ctx);
                else if (permittedRoles.contains(AppRole.USER) && tokenValid(ctx, AppRole.USER.level))
                    handler.handle(ctx);
                else if (permittedRoles.contains(AppRole.MODERATOR) && tokenValid(ctx, AppRole.MODERATOR.level))
                    handler.handle(ctx);
                else if (permittedRoles.contains(AppRole.ADMINISTRATOR) && tokenValid(ctx, AppRole.ADMINISTRATOR.level))
                    handler.handle(ctx);
                else if (permittedRoles.isEmpty()) handler.handle(ctx);
                else ctx.status(401).json(new UnauthorizedResponse(INVALID_AUTH_ERROR));
            });
        }).routes(() -> {
            path(REGISTER_PATH, () -> {
                post(LoginController::register, new HashSet<>(Arrays.asList(AppRole.ANYONE)));
            });
            path(LOGIN_PATH, () -> {
                post(LoginController::login, new HashSet<>(Arrays.asList(AppRole.ANYONE)));
            });
            path(USERS_PATH, () -> {
                get(UserController::getAll, new HashSet<>(Arrays.asList(AppRole.USER)));
                path(USER_PATH, () -> {
                    get(UserController::getOne, new HashSet<>(Arrays.asList(AppRole.USER)));
                    patch(UserController::update, new HashSet<>(Arrays.asList(AppRole.USER)));
                    delete(UserController::delete, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                });
            });
            path(REPORT_PATH, () -> {
                post(ReportController::report, new HashSet<>(Arrays.asList(AppRole.USER)));
            });
            path(ADMIN_PATH, () -> {
                path(RANKS_PATH, () -> {
                    get(RankController::getAll, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                    path(GRANT_RANK_PATH, () -> {
                        post(RankController::grant, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                    });
                    path(REVOKE_RANK_PATH, () -> {
                        post(RankController::revoke, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                    });
                });
                path(REPORTS_PATH, () -> {
                    path(REPORT_TYPES_PATH, () -> {
                        get(ReportController::getTypes, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                    });
                    path(REPORTS_ID_PATH, () -> {
                        get(ReportController::getOne, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                        post(ReportController::updateReportStatus, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                    });
                    get(ReportController::getAll, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                });
                path(BANS_PATH, () -> {
                    path(BANS_ID_PATH, () -> {
                        get(BanController::getOne, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                        post(BanController::updateBan, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                    });
                    get(BanController::getAll, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                    post(BanController::ban, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                });
            });
        });
    }

    static boolean tokenValid(Context ctx, int minLevel) {
        Optional<String> token = JavalinJWT.getTokenFromHeader(ctx);
        if (token.isPresent()) {
            try {
                DecodedJWT decodedJWT = JWTProvider.getInstance().verifyToken(token.get());
                int tokenLevel = decodedJWT.getClaims().get(JWTProvider.USER_LEVEL).as(int.class);
                if (tokenLevel >= minLevel) {
                    return true;
                }
            } catch (JWTSecretMissingException | JWTDecodeException e) {
                logger.severe(e.getMessage());
            }
        }
        return false;
    }

    public void start() throws IOException {
        server.start(port);
        logger.info("Server started, listening on " + port);
        logger.info("Check out ReDoc docs at http://localhost:" + port + REDOC_PATH);
        logger.info("Check out Swagger UI docs at http://localhost:" + port + SWAGER_UI_PATH);

    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.stop();
        }
    }


    private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version(API_VERSION).description(API_DESCRIPTION);
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor(API_ANNOTATIONS_PACKAGE)
                .path(SWAGER_DOCS_PATH) // endpoint for OpenAPI json
                .swagger(new SwaggerOptions(SWAGER_UI_PATH)) // endpoint for swagger-ui
                .reDoc(new ReDocOptions(REDOC_PATH)) // endpoint for redoc
                .defaultDocumentation(doc -> {
                    doc.json("500", InternalServerErrorResponse.class);
                    doc.json("503", ServiceUnavailableResponse.class);
                });
        return new OpenApiPlugin(options);
    }

    public static int getUserIDFromRequestToken(Context ctx) {
        Optional<String> token = JavalinJWT.getTokenFromHeader(ctx);
        if (token.isPresent()) {

            try {
                DecodedJWT decodedJWT = JWTProvider.getInstance().verifyToken(token.get());
                return decodedJWT.getClaims().get(JWTProvider.USER_ID).as(int.class);
            } catch (JWTSecretMissingException e) {
                logger.severe(e.getMessage());
            }
        }
        return -1;
    }

}
