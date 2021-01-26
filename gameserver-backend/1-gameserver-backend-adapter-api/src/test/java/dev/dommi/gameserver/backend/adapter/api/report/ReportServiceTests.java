package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.ReportTypeEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTests {

    @Test
    public void convertToReportFromTest() {

        ReportEntity entity = new ReportEntity(1, 1, 2, "ExampleReason", new ReportTypeEntity(1, "ExampleType"), true);
        Report report = ReportService.convertToReportFrom(entity);
        assertNotNull(report);
        assertEquals(entity.getId(), report.id);
        assertEquals(entity.getCreator(), report.creator);
        assertEquals(entity.getReported(), report.reported);
        assertEquals(entity.getReason(), report.reason);
        assertEquals(entity.getType().getId(), report.type.id);
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
            assertEquals(entity.getCreator(), report.creator);
            assertEquals(entity.getReported(), report.reported);
            assertEquals(entity.getReason(), report.reason);
            assertEquals(entity.getType().getId(), report.type.id);
            assertEquals(entity.getType().getName(), report.type.name);
            assertEquals(entity.isOpen(), report.open);
        }
    }


    @Test
    public void convertToReportTypeFromTest() {
        ReportTypeEntity entity = new ReportTypeEntity(1, "ExampleType");
        ReportType type = ReportService.convertToReportTypeFrom(entity);

        assertNotNull(type);
        assertEquals(entity.getId(), type.id);
        assertEquals(entity.getName(), type.name);
    }

    @Test
    public void convertToReportTypeCollectionFromTest() {
        Collection<ReportTypeEntity> entities = createReportTypeEntities();
        Collection<ReportType> types = ReportService.convertToReportTypeCollectionFrom(entities);

        for (int i = 0; i < entities.size(); i++) {
            ReportType type = (ReportType) types.toArray()[i];
            ReportTypeEntity entity = (ReportTypeEntity) entities.toArray()[i];

            assertNotNull(type);
            assertEquals(entity.getId(), type.id);
            assertEquals(entity.getName(), type.name);
        }
    }

    private Collection<ReportEntity> createReportEntities() {
        List<ReportEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new ReportEntity(i, 1 + i, 2 + i, "ExampleReason" + i, new ReportTypeEntity(1 + i, "ExampleType" + i), true));
        }
        return entities;
    }

    private Collection<ReportTypeEntity> createReportTypeEntities() {
        List<ReportTypeEntity> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new ReportTypeEntity(1 + i, "ExampleType" + i));
        }
        return entities;
    }
}
