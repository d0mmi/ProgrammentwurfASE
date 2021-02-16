package dev.dommi.gameserver.backend.plugin.api.server;


import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTSecretMissingException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.ServiceUnavailableResponse;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import javalinjwt.JavalinJWT;

import java.util.Optional;
import java.util.logging.Logger;

public class APIServer {
    private static final Logger logger = Logger.getLogger(APIServer.class.getName());
    private APIServerConfig apiServerConfig;
    private Javalin server;

    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final String API_VERSION = "1.0";
    private static final String API_DESCRIPTION = "UserEntity API";
    private static final String API_ANNOTATIONS_PACKAGE = "dev.dommi.gameserver.backend.plugin.api.services";
    private static final String SWAGER_DOCS_PATH = "/swagger-docs";
    private static final String SWAGER_UI_PATH = "/swagger-ui";
    private static final String REDOC_PATH = "/redoc";

    public APIServer(APIServerConfig apiServerConfig) {
        this.apiServerConfig = apiServerConfig;
        this.server = initServer();
    }

    private Javalin initServer() {
        return Javalin.create(config -> {
            config.registerPlugin(getConfiguredOpenApiPlugin());
            config.defaultContentType = DEFAULT_CONTENT_TYPE;
            config.accessManager(new APIServerAccessManager());
        }).routes(new APIServerEndpointGroup(apiServerConfig));
    }


    public void start() {
        server.start(apiServerConfig.getPort());
        logger.info("Server started, listening on " + apiServerConfig.getPort());
        logger.info("Check out ReDoc docs at http://localhost:" + apiServerConfig.getPort() + REDOC_PATH);
        logger.info("Check out Swagger UI docs at http://localhost:" + apiServerConfig.getPort() + SWAGER_UI_PATH);

    }

    public void stop() {
        if (server != null) {
            server.stop();
        }
    }


    private static OpenApiPlugin getConfiguredOpenApiPlugin() {
        Info info = new Info().version(API_VERSION).description(API_DESCRIPTION);
        OpenApiOptions options = new OpenApiOptions(info)
                .activateAnnotationScanningFor(API_ANNOTATIONS_PACKAGE)
                .path(SWAGER_DOCS_PATH)
                .swagger(new SwaggerOptions(SWAGER_UI_PATH))
                .reDoc(new ReDocOptions(REDOC_PATH))
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
