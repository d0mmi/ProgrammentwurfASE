package dev.dommi.gameserver.backend.domain.entities;


import dev.dommi.gameserver.backend.domain.mocks.BanRepositoryMock;
import dev.dommi.gameserver.backend.domain.mocks.RankRepositoryMock;
import dev.dommi.gameserver.backend.domain.mocks.UserRepositoryMock;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTests {

    @Test
    public void isBannedTest() throws SQLException {
        UserEntity userEntity = new UserEntity(0, "", "", null);
        assertFalse(userEntity.isBanned(new BanRepositoryMock()));
    }

    @Test
    public void grantRankTest() throws SQLException {
        RankVO oldRank = new RankVO(1,"old", 1);
        UserEntity userEntity = new UserEntity(0, "", "", oldRank);

        userEntity.grantRank(1, new RankRepositoryMock());

        assertNotEquals(oldRank, userEntity.getRank());
    }

    @Test
    public void revokeRankTest() throws SQLException {
        RankVO oldRank = new RankVO(1,RankType.USER.value, 1);
        UserEntity userEntity = new UserEntity(0, "", "", oldRank);

        userEntity.revokeRank(new RankRepositoryMock());

        assertTrue(userEntity.getRank().getName().equalsIgnoreCase(RankType.USER.value));
    }

    @Test
    public void modifyTest() throws SQLException {
        UserEntity userEntity = new UserEntity(0, "", "", null);
        String name = "TestUser";
        String email = "test@test.com";
        String pw = "Passw0rd#";

        assertTrue(userEntity.modify(name, email, pw, new UserRepositoryMock()));
        assertEquals(userEntity.getName(), name);
        assertEquals(userEntity.getEmail(), email);
    }

    @Test
    public void invalidModifyTest() throws SQLException {
        UserEntity userEntity = new UserEntity(0, "", "", null);
        String name = "TestUser";
        String email = "test@test.com";
        String pw = "Invalid";

        assertFalse(userEntity.modify(name, email, pw, new UserRepositoryMock()));
        assertNotEquals(userEntity.getName(), name);
        assertNotEquals(userEntity.getEmail(), email);
    }
}
