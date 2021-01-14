package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.ReportTypeEntity;
import dev.dommi.gameserver.backend.plugin.database.report.Report;
import dev.dommi.gameserver.backend.plugin.database.report.ReportController;
import dev.dommi.gameserver.backend.plugin.database.report.ReportType;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportRepositoryImplTests {

    @Test
    public void convertToReportEntityFromTest() throws SQLException {

        ReportController controller = mock(ReportController.class);
        when(controller.getReportType(1)).thenReturn(new ReportType(1, "ExampleType"));

        Report report = new Report(1, 1, 2, "ExampleReason", 1, true);
        ReportEntity entity = new ReportRepositoryImpl(controller).convertToReportEntityFrom(report);

        assertNotNull(entity);
        assertEquals(report.id, entity.id);
        assertEquals(report.creator, entity.creator);
        assertEquals(report.reported, entity.reported);
        assertEquals(report.reason, entity.reason);
        assertEquals(report.typeId, entity.type.id);
        assertEquals(report.open, entity.open);
    }

    @Test
    public void convertToReportEntityCollectionFromTest() throws SQLException {

        ReportController controller = mock(ReportController.class);
        when(controller.getReportType(anyInt())).thenReturn(new ReportType(anyInt(), "ExampleType"));

        Collection<Report> reports = createReports();
        Collection<ReportEntity> entities = new ReportRepositoryImpl(controller).convertToReportEntityCollectionFrom(reports);

        for (int i = 0; i < reports.size(); i++) {
            Report report = (Report) reports.toArray()[i];
            ReportEntity entity = (ReportEntity) entities.toArray()[i];


            assertNotNull(entity);
            assertEquals(report.id, entity.id);
            assertEquals(report.creator, entity.creator);
            assertEquals(report.reported, entity.reported);
            assertEquals(report.reason, entity.reason);
            assertEquals(report.open, entity.open);
        }
    }


    @Test
    public void convertToReportTypeEntityFromTest() {
        ReportType type = new ReportType(1, "ExampleType");
        ReportTypeEntity entity = ReportRepositoryImpl.convertToReportTypeEntityFrom(type);

        assertNotNull(entity);
        assertEquals(type.id, entity.id);
        assertEquals(type.name, entity.name);
    }

    @Test
    public void convertToReportTypeEntityCollectionFromTest() {
        Collection<ReportType> types = createReportTypes();
        Collection<ReportTypeEntity> entities = ReportRepositoryImpl.convertToReportTypeEntityCollectionFrom(types);

        for (int i = 0; i < types.size(); i++) {
            ReportType type = (ReportType) types.toArray()[i];
            ReportTypeEntity entity = (ReportTypeEntity) entities.toArray()[i];

            assertNotNull(entity);
            assertEquals(type.id, entity.id);
            assertEquals(type.name, entity.name);
        }
    }

    private Collection<Report> createReports() {
        List<Report> reports = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            reports.add(new Report(i, 1 + i, "ExampleReason" + i, 2 + i, true));
        }
        return reports;
    }

    private Collection<ReportType> createReportTypes() {
        List<ReportType> types = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            types.add(new ReportType(1 + i, "ExampleType" + i));
        }
        return types;
    }

}
