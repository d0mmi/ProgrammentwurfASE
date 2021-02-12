package dev.dommi.gameserver.backend.plugin.api.server;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dommi.gameserver.backend.plugin.api.auth.AppRole;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import dev.dommi.gameserver.backend.plugin.api.auth.JWTSecretMissingException;
import io.javalin.core.security.AccessManager;
import io.javalin.core.security.Role;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import javalinjwt.JavalinJWT;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

public class APIServerAccessManager implements AccessManager {
    private static final Logger logger = Logger.getLogger(APIServerAccessManager.class.getName());
    private static final String INVALID_AUTH_ERROR = "Missing, invalid token or not enough permissions";

    @Override
    public void manage(@NotNull Handler handler, @NotNull Context ctx, @NotNull Set<Role> permittedRoles) throws Exception {
        if (permittedRoles.contains(AppRole.ANYONE)) handler.handle(ctx);
        else if (permittedRoles.contains(AppRole.USER) && tokenValid(ctx, AppRole.USER.level))
            handler.handle(ctx);
        else if (permittedRoles.contains(AppRole.MODERATOR) && tokenValid(ctx, AppRole.MODERATOR.level))
            handler.handle(ctx);
        else if (permittedRoles.contains(AppRole.ADMINISTRATOR) && tokenValid(ctx, AppRole.ADMINISTRATOR.level))
            handler.handle(ctx);
        else if (permittedRoles.isEmpty()) handler.handle(ctx);
        else ctx.status(401).json(new UnauthorizedResponse(INVALID_AUTH_ERROR));
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
}
