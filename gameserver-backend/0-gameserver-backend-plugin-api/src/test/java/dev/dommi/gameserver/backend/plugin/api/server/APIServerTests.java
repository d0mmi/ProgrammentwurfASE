package dev.dommi.gameserver.backend.plugin.api.server;

import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class APIServerTests {

    @Test
    @SetEnvironmentVariable(key = JWTProvider.JWT_SECRET, value = "secret")
    public void tokenValidTest() {

        Context ctx = mock(Context.class);
        when(ctx.header("Authorization")).thenAnswer(I -> "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsZXZlbCI6NTAsImlkIjo1fQ.nNbH4_9pkwitj7hm4AUfrg1Zsv2KhGq595RouB8Mapg");

        assertTrue(APIServerAccessManager.tokenValid(ctx, 1));
    }

    @Test
    @SetEnvironmentVariable(key = JWTProvider.JWT_SECRET, value = "secret")
    public void tokenMinLevelInvalidTest() {

        Context ctx = mock(Context.class);
        when(ctx.header("Authorization")).thenAnswer(I -> "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsZXZlbCI6NTAsImlkIjo1fQ.nNbH4_9pkwitj7hm4AUfrg1Zsv2KhGq595RouB8Mapg");

        assertFalse(APIServerAccessManager.tokenValid(ctx, 100));
    }

    @Test
    @SetEnvironmentVariable(key = JWTProvider.JWT_SECRET, value = "secret")
    public void tokenInvalidTest() {

        Context ctx = mock(Context.class);
        when(ctx.header("Authorization")).thenAnswer(I -> "Bearer invalidToken");

        assertFalse(APIServerAccessManager.tokenValid(ctx, 50));
    }

}
