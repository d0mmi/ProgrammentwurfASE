package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTests {

    @Test
    public void convertToReportFromTest() {

        ReportEntity entity = new ReportEntity(1, new UserEntity(1, "test", "test@test.com", new RankVO("test", 0)), new UserEntity(2, "test2", "test2@test.com", new RankVO("test", 0)), "ExampleReason", new ReportTypeVO("ExampleType"), true);
        Report report = ReportService.convertToReportFrom(entity);
        assertNotNull(report);
        assertEquals(entity.getId(), report.id);
        assertEquals(entity.getCreator().getId(), report.creator.id);
        assertEquals(entity.getReported().getId(), report.reported.id);
        assertEquals(entity.getReason(), report.reason);
        assertEquals(entity.getType().getName(), report.type.name);
        assertEquals(entity.isOpen(), report.open);
    }

    @Test
    public void convertToReportCollectionFromTest() {
        Collection<ReportEntity> entities = createReportEntities();
        Collection<Report> reports = ReportService.convertToReportCollectionFrom(entities);

        for (int i = 0; i < entities.size(); i++) {
            Report report = (Report) reports.toArray()[i];
            ReportEntity entity = (ReportEntity) entities.toArray()[i];

            assertNotNull(report);
            assertEquals(entity.getId(), report.id);
            assertEquals(entity.getCreator().getId(), report.creator.id);
            assertEquals(entity.getReported().getId(), report.reported.id);
            assertEquals(entity.getReason(), report.reason);
            assertEquals(entity.getType().getName(), report.type.name);
            assertEquals(entity.isOpen(), report.open);
        }
    }


    @Test
    public void convertToReportTypeFromTest() {
        ReportTypeVO entity = new ReportTypeVO("ExampleType");
        ReportType type = ReportService.convertToReportTypeFrom(entity);

        assertNotNull(type);
        assertEquals(entity.getName(), type.name);
    }

    @Test
    public void convertToReportTypeCollectionFromTest() {
        Collection<ReportTypeVO> entities = createReportTypeEntities();
        Collection<ReportType> types = ReportService.convertToReportTypeCollectionFrom(entities);

        for (int i = 0; i < entities.size(); i++) {
            ReportType type = (ReportType) types.toArray()[i];
            ReportTypeVO entity = (ReportTypeVO) entities.toArray()[i];

            assertNotNull(type);
            assertEquals(entity.getName(), type.name);
        }
    }

    private Collection<ReportEntity> createReportEntities() {
        List<ReportEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new ReportEntity(i, new UserEntity(2 + i, "test" + i, "test@test.com", new RankVO("test", 0)), new UserEntity(3 + i, "test" + i, "test@test.com", new RankVO("test", 0)), "ExampleReason" + i, new ReportTypeVO("ExampleType" + i), true));
        }
        return entities;
    }

    private Collection<ReportTypeVO> createReportTypeEntities() {
        List<ReportTypeVO> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new ReportTypeVO("ExampleType" + i));
        }
        return entities;
    }
}
