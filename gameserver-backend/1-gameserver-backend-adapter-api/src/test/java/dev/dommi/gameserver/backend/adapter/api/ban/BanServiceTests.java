package dev.dommi.gameserver.backend.adapter.api.ban;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BanServiceTests {

    @Test
    public void convertToBanFromTest() {
        BanEntity entity = new BanEntity(1, 2, 3, "exampleReason", new Date(), true);
        Ban ban = BanService.convertToBanFrom(entity);

        assertNotNull(ban);
        assertEquals(entity.getId(), ban.id);
        assertEquals(entity.getUserId(), ban.userId);
        assertEquals(entity.getBannedById(), ban.bannedById);
        assertEquals(entity.getReason(), ban.reason);
        assertEquals(entity.getUntil(), ban.until);
        assertEquals(entity.isActive(), ban.active);
    }

    @Test
    public void convertToBanCollectionFromTest() {
        Collection<BanEntity> entities = createEntities();
        Collection<Ban> bans = BanService.convertToBanCollectionFrom(entities);

        for (int i = 0; i < entities.size(); i++) {
            Ban ban = (Ban) bans.toArray()[i];
            BanEntity entity = (BanEntity) entities.toArray()[i];

            assertNotNull(ban);
            assertEquals(entity.getId(), ban.id);
            assertEquals(entity.getUserId(), ban.userId);
            assertEquals(entity.getBannedById(), ban.bannedById);
            assertEquals(entity.getReason(), ban.reason);
            assertEquals(entity.getUntil(), ban.until);
            assertEquals(entity.isActive(), ban.active);
        }
    }

    private Collection<BanEntity> createEntities() {
        List<BanEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new BanEntity(1 + i, 2 + i, 3 + i, "exampleReason" + i, new Date(), true));
        }
        return entities;
    }

}
