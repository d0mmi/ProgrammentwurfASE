package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTests {

    @Test
    public void convertToUserEntityFromTest() {


        User user = new User(1, "TestUser", "test@example.com", "pw");
        UserRankAggregate aggregate = UserMapper.getUserRankAggregateFrom(user, new Rank(1, "test", 50));

        assertNotNull(aggregate);
        assertEquals(user.id, aggregate.getUserId());
        assertEquals(user.name, aggregate.getUserName());
        assertEquals(user.email, aggregate.getEmail());
    }

    @Test
    public void convertToUserEntityCollectionFromTest() {

        Collection<User> users = createUsers();

        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.toArray()[i];

            UserRankAggregate aggregate = UserMapper.getUserRankAggregateFrom(user, new Rank(1, "test", 50));

            assertNotNull(aggregate);
            assertEquals(user.id, aggregate.getUserId());
            assertEquals(user.name, aggregate.getUserName());
            assertEquals(user.email, aggregate.getEmail());
        }

    }

    private Collection<User> createUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "TestUser" + i, "test" + i + "@example.com", "pw"));
        }
        return users;
    }

}
