package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.mocks.BanRepositoryMock;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BanEntityTests {

    @Test
    public void updateBanTest() throws SQLException {
        BanEntity entity = new BanEntity(0, 0, 0, "old", new Date(), true);
        String newReason = "newReason";
        Date newDate = new Date();
        entity.update(newReason, newDate, false, new BanRepositoryMock());
        assertEquals(newReason, entity.getReason());
        assertEquals(newDate, entity.getUntil());
        assertFalse(entity.isActive());
    }

    @Test
    public void updateNullBanTest() throws SQLException {
        BanEntity entity = new BanEntity(0, 0, 0, "old", new Date(), true);
        entity.update(null, null, true, new BanRepositoryMock());
        assertNotNull(entity.getReason());
        assertNotNull(entity.getUntil());
    }

}
