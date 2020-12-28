package dev.dommi.gameserver.backend.plugin.api.server;


import dev.dommi.gameserver.backend.plugin.api.services.user.UserController;
import io.javalin.Javalin;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.ServiceUnavailableResponse;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.IOException;
import java.util.logging.Logger;

public class APIServer {
    private static final Logger logger = Logger.getLogger(APIServer.class.getName());
    private int port;
    private Javalin server;

    private static final String API_PORT = "API_PORT";
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final String USERS_PATH = "users";
    private static final String USER_PATH = ":userId";
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
        }).routes(() -> {
            path(USERS_PATH, () -> {
                get(UserController::getAll);
                post(UserController::create);
                path(USER_PATH, () -> {
                    get(UserController::getOne);
                    patch(UserController::update);
                    delete(UserController::delete);
                });
            });
        });
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
}
