package dev.dommi.gameserver.backend.domain.entities;


import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.mocks.RankRepositoryMock;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTests {

    @Test
    public void grantRankTest() {
        UserEntity entity = new UserEntity(1, "TestUser", "test@example.com");
        RankVO old = new RankVO(2, "oldRank", 50);
        UserRankAggregate aggregate = new UserRankAggregate(entity, old);

        aggregate.grantRank(new RankVO(3, "newRank", 33));

        assertNotEquals(old.getId(), aggregate.getRankId());
        assertNotEquals(old.getName(), aggregate.getRankName());
        assertNotEquals(old.getLevel(), aggregate.getRankLevel());
    }

    @Test
    public void modifyTest() {
        UserEntity entity = new UserEntity(1, "User", "test1@example.com");
        RankVO rank = new RankVO(2, "Rank", 50);
        UserRankAggregate aggregate = new UserRankAggregate(entity, rank);
        String name = "TestUser";
        String email = "test@test.com";
        String pw = "Passw0rd#";

        assertTrue(aggregate.modifyUser(name, email));
        assertEquals(aggregate.getUserName(), name);
        assertEquals(aggregate.getEmail(), email);
    }

    @Test
    public void invalidModifyTest() {
        UserEntity entity = new UserEntity(1, "User", "test@example.com");
        RankVO rank = new RankVO(2, "Rank", 50);
        UserRankAggregate aggregate = new UserRankAggregate(entity, rank);
        String name = "TestUser";
        String email = "invalid";

        assertFalse(aggregate.modifyUser(name, email));
        assertNotEquals(aggregate.getUserName(), name);
        assertNotEquals(aggregate.getEmail(), email);
    }
}
