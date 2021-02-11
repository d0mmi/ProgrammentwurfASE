package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
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

        ReportDatabaseController controller = mock(ReportDatabaseController.class);
        UserRepositoryImpl userRepository = mock(UserRepositoryImpl.class);
        when(controller.getReportType(1)).thenReturn(new ReportType(1, "ExampleType"));
        when(userRepository.findById(1)).thenReturn(new UserEntity(1, "", "", null));
        when(userRepository.findById(2)).thenReturn(new UserEntity(2, "", "", null));

        Report report = new Report(1, 1, 2, "ExampleReason", 1, true);
        ReportEntity entity = new ReportRepositoryImpl(controller, userRepository).convertToReportEntityFrom(report);

        assertNotNull(entity);
        assertEquals(report.id, entity.getId());
        assertEquals(report.creator, entity.getCreator().getId());
        assertEquals(report.reported, entity.getReported().getId());
        assertEquals(report.reason, entity.getReason());
        assertEquals(report.open, entity.isOpen());
    }

    @Test
    public void convertToReportEntityCollectionFromTest() throws SQLException {

        ReportDatabaseController controller = mock(ReportDatabaseController.class);
        UserRepositoryImpl userRepository = mock(UserRepositoryImpl.class);

        Collection<Report> reports = createReports();
        Collection<ReportEntity> entities = new ReportRepositoryImpl(controller, userRepository).convertToReportEntityCollectionFrom(reports);

        for (int i = 0; i < reports.size(); i++) {
            Report report = (Report) reports.toArray()[i];
            ReportEntity entity = (ReportEntity) entities.toArray()[i];


            assertNotNull(entity);
            assertEquals(report.id, entity.getId());
            assertEquals(report.reason, entity.getReason());
            assertEquals(report.open, entity.isOpen());
        }
    }


    @Test
    public void convertToReportTypeEntityFromTest() {
        ReportType type = new ReportType(1, "ExampleType");
        ReportTypeVO entity = ReportRepositoryImpl.convertToReportTypeEntityFrom(type);

        assertNotNull(entity);
        assertEquals(type.name, entity.getName());
    }

    @Test
    public void convertToReportTypeEntityCollectionFromTest() {
        Collection<ReportType> types = createReportTypes();
        Collection<ReportTypeVO> entities = ReportRepositoryImpl.convertToReportTypeEntityCollectionFrom(types);

        for (int i = 0; i < types.size(); i++) {
            ReportType type = (ReportType) types.toArray()[i];
            ReportTypeVO entity = (ReportTypeVO) entities.toArray()[i];

            assertNotNull(entity);
            assertEquals(type.name, entity.getName());
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
