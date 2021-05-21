package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.application.mocks.BanRepositoryMock;
import dev.dommi.gameserver.backend.application.mocks.UserRepositoryMock;
import dev.dommi.gameserver.backend.domain.services.ban.BanService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BanTests {

    @Test
    public void checkUserBanTest() {

        CheckUserBan checkUserBan = new CheckUserBan(new UserRepositoryMock(), new BanRepositoryMock());

        assertTrue(checkUserBan.isUserBanned("banned@example.com"));

    }

}
