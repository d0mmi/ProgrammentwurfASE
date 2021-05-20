package dev.dommi.gameserver.backend.adapter.api.user;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserBridgeTests {

    @Test
    public void convertToUserFromTest() {
        UserEntity entity = new UserEntity(1, "TestUser", "test@example.com");
        UserRankAggregate aggregate = new UserRankAggregate(entity, new RankVO(2,"TestRank",50));
        User user = UserBridge.convertToUserFrom(aggregate);
        assertNotNull(user);
        assertEquals(aggregate.getUserId(), user.id);
        assertEquals(aggregate.getUserName(), user.name);
        assertEquals(aggregate.getEmail(), user.email);
        assertEquals(aggregate.getRankLevel(), user.level);
    }

    @Test
    public void convertToUserCollectionFromTest() {
        Collection<UserRankAggregate> aggregates = createEntities();
        Collection<User> users = UserBridge.convertToUserCollectionFromAggregate(aggregates);

        for (int i = 0; i < aggregates.size(); i++) {
            User user = (User) users.toArray()[i];
            UserRankAggregate aggregate = (UserRankAggregate) aggregates.toArray()[i];
            assertNotNull(user);
            assertEquals(aggregate.getUserId(), user.id);
            assertEquals(aggregate.getUserName(), user.name);
            assertEquals(aggregate.getEmail(), user.email);
            assertEquals(aggregate.getRankLevel(), user.level);
        }

    }

    private Collection<UserRankAggregate> createEntities() {
        List<UserRankAggregate> aggregates = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserEntity entity = new UserEntity(i, "TestUser"+i, i+"test@example.com");
            UserRankAggregate aggregate = new UserRankAggregate(entity, new RankVO(i+5,"TestRank"+i,50+i));
            aggregates.add(aggregate);
        }
        return aggregates;
    }
}
