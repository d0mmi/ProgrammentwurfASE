package dev.dommi.gameserver.backend.plugin.api.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.dommi.gameserver.backend.adapter.api.user.User;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ClearEnvironmentVariable;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

public class JWTProviderTests {


    @Test
    @SetEnvironmentVariable(key = JWTProvider.JWT_SECRET, value = "secret")
    public void generateTokenTest() throws JWTSecretMissingException {
        JWTProvider provider = JWTProvider.getInstance();

        String token = provider.generateToken(new User(5, "TestUser", "test@example.com", 50));
        assertNotNull(token);
        assertFalse(token.isEmpty());

    }

    @Test
    @SetEnvironmentVariable(key = JWTProvider.JWT_SECRET, value = "secret")
    public void verifyTokenTest() throws JWTSecretMissingException {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsZXZlbCI6NTAsImlkIjo1fQ.nNbH4_9pkwitj7hm4AUfrg1Zsv2KhGq595RouB8Mapg";
        JWTProvider provider = JWTProvider.getInstance();

        DecodedJWT decodedJWT = provider.verifyToken(token);
        assertNotNull(decodedJWT);
        assertEquals(decodedJWT.getClaim(JWTProvider.USER_ID).as(int.class), 5);
        assertEquals(decodedJWT.getClaim(JWTProvider.USER_LEVEL).as(int.class), 50);

    }

    @Test
    @ClearEnvironmentVariable(key = JWTProvider.JWT_SECRET)
    public void JWTSecretMissingExceptionTest() {
        assertThrows(JWTSecretMissingException.class, JWTProvider::new);

    }

}
