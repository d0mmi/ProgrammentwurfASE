package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.application.mocks.RankRepositoryMock;
import dev.dommi.gameserver.backend.application.mocks.UserRepositoryMock;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class LoginTests {

    @Test
    public void registerUserTest() {

        assertTrue(new RegisterUser(new UserRepositoryMock(true), new RankRepositoryMock()).registerUser("test", "test@test.com", "Passw0rd#"));
    }

    @Test
    public void registerUserExistsTest() {

        assertFalse(new RegisterUser(new UserRepositoryMock(), new RankRepositoryMock()).registerUser("test", "test@test.com", "Passw0rd#"));
    }

    @Test
    public void registerUserInvalidEmailTest() {
        assertFalse(new RegisterUser(new UserRepositoryMock(true), new RankRepositoryMock()).registerUser("test", "InvalidEmail", "Passw0rd#"));
    }

    @Test
    public void registerUserTestInvalidPassword() {
        assertFalse(new RegisterUser(new UserRepositoryMock(true), new RankRepositoryMock()).registerUser("test", "test@test.com", "1234"));
    }

}
