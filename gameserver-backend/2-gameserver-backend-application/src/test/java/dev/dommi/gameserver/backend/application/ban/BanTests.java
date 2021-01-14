package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.application.mocks.BanRepositoryMock;
import dev.dommi.gameserver.backend.application.mocks.UserRepositoryMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BanTests {

    @Test
    public void checkUserBanTest() {

        CheckUserBan checkUserBan = new CheckUserBan(new BanRepositoryMock(), new UserRepositoryMock());

        assertTrue(checkUserBan.isBanned("banned@example.com"));

    }

}
