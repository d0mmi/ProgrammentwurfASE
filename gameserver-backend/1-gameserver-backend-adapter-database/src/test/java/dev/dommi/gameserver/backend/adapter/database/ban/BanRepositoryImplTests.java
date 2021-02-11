package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BanRepositoryImplTests {

    @Test
    public void convertToBanFromTest() throws SQLException {


        BanDatabaseController controller = mock(BanDatabaseController.class);
        UserRepositoryImpl userRepository = mock(UserRepositoryImpl.class);
        when(userRepository.findById(2)).thenReturn(new UserEntity(2,"","",null));
        when(userRepository.findById(3)).thenReturn(new UserEntity(3,"","",null));

        Ban ban = new Ban(1, 2, 3, "exampleReason", new Date(), true);
        BanEntity entity = new BanRepositoryImpl(controller, userRepository).convertToBanEntityFrom(ban);

        assertNotNull(entity);
        assertEquals(ban.id, entity.getId());
        assertEquals(ban.userId, entity.getUser().getId());
        assertEquals(ban.bannedById, entity.getBannedBy().getId());
        assertEquals(ban.reason, entity.getReason());
        assertEquals(ban.until, entity.getUntil());
        assertEquals(ban.active, entity.isActive());
    }

    @Test
    public void convertToBanCollectionFromTest() throws SQLException {

        BanDatabaseController controller = mock(BanDatabaseController.class);
        UserRepositoryImpl userRepository = mock(UserRepositoryImpl.class);
        when(userRepository.findById(anyInt())).thenReturn(new UserEntity(anyInt(),"","",null));

        Collection<Ban> bans = createBans();
        Collection<BanEntity> entities =  new BanRepositoryImpl(controller, userRepository).convertToBanEntityCollectionFrom(bans);

        for (int i = 0; i < bans.size(); i++) {
            Ban ban = (Ban) bans.toArray()[i];
            BanEntity entity = (BanEntity) entities.toArray()[i];

            assertNotNull(entity);
            assertEquals(ban.id, entity.getId());
            assertEquals(ban.reason, entity.getReason());
            assertEquals(ban.until, entity.getUntil());
            assertEquals(ban.active, entity.isActive());
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
