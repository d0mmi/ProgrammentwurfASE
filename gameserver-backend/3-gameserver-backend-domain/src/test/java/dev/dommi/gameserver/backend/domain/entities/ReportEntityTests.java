package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.mocks.ReportRepositoryMock;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ReportEntityTests {

    @Test
    public void updateStatusTest() throws SQLException {
        ReportEntity entity = new ReportEntity(0, null, null, "", null, true);
        entity.updateStatus(false, new ReportRepositoryMock());
        assertFalse(entity.isOpen());
    }

}
