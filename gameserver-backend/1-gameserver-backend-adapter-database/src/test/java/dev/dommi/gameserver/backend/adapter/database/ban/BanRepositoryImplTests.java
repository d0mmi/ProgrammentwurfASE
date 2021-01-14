package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.plugin.database.ban.Ban;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BanRepositoryImplTests {

    @Test
    public void convertToBanFromTest() {
        Ban ban = new Ban(1, 2, 3, "exampleReason", new Date(), true);
        BanEntity entity = BanRepositoryImpl.convertToBanEntityFrom(ban);

        assertNotNull(entity);
        assertEquals(ban.id, entity.id);
        assertEquals(ban.userId, entity.userId);
        assertEquals(ban.bannedById, entity.bannedById);
        assertEquals(ban.reason, entity.reason);
        assertEquals(ban.until, ban.until);
        assertEquals(ban.active, entity.active);
    }

    @Test
    public void convertToBanCollectionFromTest() {
        Collection<Ban> bans = createBans();
        Collection<BanEntity> entities = BanRepositoryImpl.convertToBanEntityCollectionFrom(bans);

        for (int i = 0; i < bans.size(); i++) {
            Ban ban = (Ban) bans.toArray()[i];
            BanEntity entity = (BanEntity) entities.toArray()[i];

            assertNotNull(entity);
            assertEquals(ban.id, entity.id);
            assertEquals(ban.userId, entity.userId);
            assertEquals(ban.bannedById, entity.bannedById);
            assertEquals(ban.reason, entity.reason);
            assertEquals(ban.until, ban.until);
            assertEquals(ban.active, entity.active);
        }
    }

    private Collection<Ban> createBans() {
        List<Ban> values = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            values.add(new Ban(1 + i, 2 + i, 3 + i, "exampleReason" + i, new Date(), true));
        }
        return values;
    }


}
