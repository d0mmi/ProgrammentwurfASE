package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportMapperTests {

    @Test
    public void convertToReportEntityFromTest() {


        Report report = new Report(1, 2, 3, "ExampleReason", 1, true);
        ReportType type = new ReportType(1, "exampleType");
        User created = new User(2, "TestUser2", "test2@example.com", "secret");
        User reported = new User(3, "TestUser3", "test3@example.com", "secret");
        ReportAggregate aggregate = ReportMapper.getReportAggregateFrom(report, type, created, reported);

        assertNotNull(aggregate);
        assertEquals(report.id, aggregate.getReportId());
        assertEquals(report.creator, aggregate.getCreator().getId());
        assertEquals(report.reported, aggregate.getReported().getId());
        assertEquals(report.reason, aggregate.getReason());
        assertEquals(report.open, aggregate.isOpen());
    }


    @Test
    public void convertToReportTypeEntityFromTest() {
        ReportType type = new ReportType(1, "ExampleType");
        ReportTypeVO entity = ReportMapper.getReportTypeVOFrom(type);

        assertNotNull(entity);
        assertEquals(type.id, entity.getId());
        assertEquals(type.name, entity.getName());
    }

    @Test
    public void convertToReportTypeEntityCollectionFromTest() {
        Collection<ReportType> types = createReportTypes();
        Collection<ReportTypeVO> entities = ReportMapper.getReportTypeVOCollectionFrom(types);

        for (int i = 0; i < types.size(); i++) {
            ReportType type = (ReportType) types.toArray()[i];
            ReportTypeVO entity = (ReportTypeVO) entities.toArray()[i];

            assertNotNull(entity);
            assertEquals(type.id, entity.getId());
            assertEquals(type.name, entity.getName());
        }
    }


    private Collection<ReportType> createReportTypes() {
        List<ReportType> types = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            types.add(new ReportType(1 + i, "ExampleType" + i));
        }
        return types;
    }

}
