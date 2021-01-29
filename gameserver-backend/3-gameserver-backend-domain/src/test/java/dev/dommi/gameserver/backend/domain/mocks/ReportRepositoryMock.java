package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.sql.SQLException;
import java.util.Collection;

public class ReportRepositoryMock implements ReportRepository {
    @Override
    public Collection<ReportEntity> getAllReports() throws SQLException {
        return null;
    }

    @Override
    public Collection<ReportEntity> getReportsCreatedBy(int userId) throws SQLException {
        return null;
    }

    @Override
    public Collection<ReportEntity> getReportsFor(int userId) throws SQLException {
        return null;
    }

    @Override
    public ReportEntity getReport(int reportId) throws SQLException {
        return null;
    }

    @Override
    public Collection<ReportTypeVO> getReportTypes() throws SQLException {
        return null;
    }

    @Override
    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) throws SQLException {

    }

    @Override
    public int getReportTypeIdByName(String name) throws SQLException {
        return 0;
    }

    @Override
    public void updateReportStatus(int reportId, boolean status) throws SQLException {

    }
}
