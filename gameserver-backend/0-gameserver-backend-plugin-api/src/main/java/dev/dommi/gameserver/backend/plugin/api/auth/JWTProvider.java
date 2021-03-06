package dev.dommi.gameserver.backend.plugin.api.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dommi.gameserver.backend.adapter.api.user.User;

public final class JWTProvider {
    public static final String USER_ID = "id";
    public static final String USER_LEVEL = "level";
    public static final String JWT_SECRET = "JWT_SECRET";
    private static JWTProvider instance;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    private JWTProvider() throws JWTSecretMissingException {
        init();
    }

    void init() throws JWTSecretMissingException {
        String secret = System.getenv(JWT_SECRET);
        if (secret == null) {
            throw new JWTSecretMissingException("You need to provide the JWT Secret as Environment Variable with the Key: " + JWT_SECRET);
        }
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm).build();
    }

    public String generateToken(User user) {
        JWTCreator.Builder token = JWT.create()
                .withClaim(USER_ID, user.id)
                .withClaim(USER_LEVEL, user.level);
        return token.sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        return verifier.verify(token);
    }

    public static JWTProvider getInstance() throws JWTSecretMissingException {
        if (instance == null) instance = new JWTProvider();
        return instance;
    }
}
