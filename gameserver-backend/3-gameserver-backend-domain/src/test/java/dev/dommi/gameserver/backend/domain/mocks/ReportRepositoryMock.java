package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.util.Collection;

public class ReportRepositoryMock implements ReportRepository {
    @Override
    public Collection<ReportAggregate> getAllReports() {
        return null;
    }

    @Override
    public Collection<ReportAggregate> getReportsCreatedBy(int userId) {
        return null;
    }

    @Override
    public Collection<ReportAggregate> getReportsFor(int userId) {
        return null;
    }

    @Override
    public ReportAggregate getReport(int reportId) {
        return null;
    }

    @Override
    public Collection<ReportTypeVO> getReportTypes() {
        return null;
    }

    @Override
    public boolean reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        return true;
    }

    @Override
    public int getReportTypeIdByName(String name) {
        return 0;
    }

    @Override
    public boolean updateReportStatus(int reportId, boolean status) {
        return true;
    }
}
