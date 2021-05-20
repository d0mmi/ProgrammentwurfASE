package dev.dommi.gameserver.backend.adapter.api.ban;

import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BanBridgeTests {

    @Test
    public void convertToBanFromTest() {
        BanAggregate aggregate = new BanAggregate(new BanEntity(1, "exampleReason", new Date(), true), new UserEntity(1, "test", "test@test.com"), new UserEntity(2, "test2", "test2@test.com"));
        Ban ban = BanBridge.convertToBanFrom(aggregate);

        assertNotNull(ban);
        assertEquals(aggregate.getId(), ban.id);
        assertEquals(aggregate.getUser().getId(), ban.user.id);
        assertEquals(aggregate.getBannedBy().getId(), ban.bannedBy.id);
        assertEquals(aggregate.getReason(), ban.reason);
        assertEquals(aggregate.getUntil(), ban.until);
        assertEquals(aggregate.isActive(), ban.active);
    }

    @Test
    public void convertToBanCollectionFromTest() {
        Collection<BanAggregate> aggregates = createEntities();
        Collection<Ban> bans = BanBridge.convertToBanCollectionFrom(aggregates);

        for (int i = 0; i < aggregates.size(); i++) {
            Ban ban = (Ban) bans.toArray()[i];
            BanAggregate aggregate = (BanAggregate) aggregates.toArray()[i];

            assertNotNull(ban);
            assertEquals(aggregate.getId(), ban.id);
            assertEquals(aggregate.getUser().getId(), ban.user.id);
            assertEquals(aggregate.getBannedBy().getId(), ban.bannedBy.id);
            assertEquals(aggregate.getReason(), ban.reason);
            assertEquals(aggregate.getUntil(), ban.until);
            assertEquals(aggregate.isActive(), ban.active);
        }
    }

    private Collection<BanAggregate> createEntities() {
        List<BanAggregate> aggregates = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            BanAggregate aggregate = new BanAggregate(new BanEntity(i, "exampleReason", new Date(), true), new UserEntity(1 + i, "test", "test@test.com"), new UserEntity(2 + i, "test2", "test2@test.com"));
            aggregates.add(aggregate);
        }
        return aggregates;
    }

}
