package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
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
        UserEntity creator = new UserEntity(1, "TestUser", "test@example.com");
        UserEntity reported = new UserEntity(3, "TestUser3", "test3@example.com");
        ReportAggregate aggregate = new ReportAggregate(new ReportEntity(5, "exampleRason", false), creator, reported, new ReportTypeVO(4, "TestType"));
        Report report = ReportBridge.convertToReportFrom(aggregate);
        assertNotNull(report);
        assertEquals(aggregate.getReportId(), report.id);
        assertEquals(aggregate.getCreator().getId(), report.creator.id);
        assertEquals(aggregate.getReported().getId(), report.reported.id);
        assertEquals(aggregate.getReason(), report.reason);
        assertEquals(aggregate.getReportType().getName(), report.type.name);
        assertEquals(aggregate.isOpen(), report.open);
    }

    @Test
    public void convertToReportCollectionFromTest() {
        Collection<ReportAggregate> aggregates = createReportEntities();
        Collection<Report> reports = ReportBridge.convertToReportCollectionFrom(aggregates);

        for (int i = 0; i < aggregates.size(); i++) {
            Report report = (Report) reports.toArray()[i];
            ReportAggregate aggregate = (ReportAggregate) aggregates.toArray()[i];

            assertNotNull(report);
            assertEquals(aggregate.getReportId(), report.id);
            assertEquals(aggregate.getCreator().getId(), report.creator.id);
            assertEquals(aggregate.getReported().getId(), report.reported.id);
            assertEquals(aggregate.getReason(), report.reason);
            assertEquals(aggregate.getReportType().getName(), report.type.name);
            assertEquals(aggregate.isOpen(), report.open);
        }
    }


    @Test
    public void convertToReportTypeFromTest() {
        ReportTypeVO entity = new ReportTypeVO(1, "ExampleType");
        ReportType type = ReportBridge.convertToReportTypeFrom(entity);

        assertNotNull(type);
        assertEquals(entity.getName(), type.name);
    }

    @Test
    public void convertToReportTypeCollectionFromTest() {
        Collection<ReportTypeVO> entities = createReportTypeEntities();
        Collection<ReportType> types = ReportBridge.convertToReportTypeCollectionFrom(entities);

        for (int i = 0; i < entities.size(); i++) {
            ReportType type = (ReportType) types.toArray()[i];
            ReportTypeVO entity = (ReportTypeVO) entities.toArray()[i];

            assertNotNull(type);
            assertEquals(entity.getName(), type.name);
        }
    }

    private Collection<ReportAggregate> createReportEntities() {
        List<ReportAggregate> aggregates = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserEntity creator = new UserEntity(1 + i, "TestUser" + i, i + "test@example.com");
            UserEntity reported = new UserEntity(3 + i, "TestUser3" + i, i + "test3@example.com");
            ReportAggregate aggregate = new ReportAggregate(new ReportEntity(5 + i, "exampleRason", false), creator, reported, new ReportTypeVO(4 + i, "TestType"));
            aggregates.add(aggregate);
        }
        return aggregates;
    }

    private Collection<ReportTypeVO> createReportTypeEntities() {
        List<ReportTypeVO> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entities.add(new ReportTypeVO(1, "ExampleType" + i));
        }
        return entities;
    }
}
