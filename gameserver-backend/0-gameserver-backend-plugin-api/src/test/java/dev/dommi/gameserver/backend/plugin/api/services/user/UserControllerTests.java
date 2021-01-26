package dev.dommi.gameserver.backend.plugin.api.services.user;

import dev.dommi.gameserver.backend.plugin.api.auth.JWTProvider;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTests {

    @Test
    public void validPathParamUserIdTest() {
        Context ctx = mock(Context.class);
        int id = 1337;
        when(ctx.pathParam(UserController.USER_ID, Integer.class)).thenAnswer(T -> new Validator<>(id, ""));
        assertEquals(UserController.validPathParamUserId(ctx), id);
    }

    @Test
    @SetEnvironmentVariable(key = JWTProvider.JWT_SECRET, value = "secret")
    public void validUserRequestTest() {
        Context ctx = mock(Context.class);
        when(ctx.header("Authorization")).thenAnswer(I -> "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsZXZlbCI6NTAsImlkIjo1fQ.nNbH4_9pkwitj7hm4AUfrg1Zsv2KhGq595RouB8Mapg");
        int id = 420;
        when(ctx.pathParam(UserController.USER_ID, Integer.class)).thenAnswer(T -> new Validator<>(id, ""));
        assertTrue(UserController.validUserRequest(ctx));
    }

}
