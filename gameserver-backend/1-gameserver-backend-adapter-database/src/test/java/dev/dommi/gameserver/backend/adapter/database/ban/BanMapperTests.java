package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BanMapperTests {

    @Test
    public void convertToBanFromTest() {


        User user = new User(2, "TestUser2", "test2@example.com", "secret");
        User bannedBy = new User(3, "TestUser3", "test3@example.com", "secret");

        Ban ban = new Ban(1, 2, 3, "exampleReason", new Date(), true);
        BanAggregate aggregate = BanMapper.getBanAggregateFrom(ban, user, bannedBy);

        assertNotNull(aggregate);
        assertEquals(ban.id, aggregate.getId());
        assertEquals(ban.userId, aggregate.getUser().getId());
        assertEquals(ban.bannedById, aggregate.getBannedBy().getId());
        assertEquals(ban.reason, aggregate.getReason());
        assertEquals(ban.until, aggregate.getUntil());
        assertEquals(ban.active, aggregate.isActive());
    }

    @Test
    public void convertToBanCollectionFromTest() {


        List<Ban> bans = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<User> bannedBys = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User(i + 2, "TestUser2", "test2@example.com", "secret");
            User bannedBy = new User(i + 3, "TestUser3", "test3@example.com", "secret");

            Ban ban = new Ban(i, i + 2, i + 3, "exampleReason", new Date(), true);
            bans.add(ban);
            users.add(user);
            bannedBys.add(bannedBy);
        }

        for (int i = 0; i < bans.size(); i++) {
            Ban ban = (Ban) bans.get(i);
            User user = (User) users.get(i);
            User bannedBy = (User) bannedBys.get(i);
            BanAggregate aggregate = BanMapper.getBanAggregateFrom(ban, user, bannedBy);

            assertNotNull(aggregate);
            assertEquals(ban.id, aggregate.getId());
            assertEquals(ban.userId, aggregate.getUser().getId());
            assertEquals(ban.bannedById, aggregate.getBannedBy().getId());
            assertEquals(ban.reason, aggregate.getReason());
            assertEquals(ban.until, aggregate.getUntil());
            assertEquals(ban.active, aggregate.isActive());
        }
    }


}
