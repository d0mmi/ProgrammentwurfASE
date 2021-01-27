package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

import java.sql.SQLException;
import java.util.Collection;

public interface ReportRepository {

    public Collection<ReportEntity> getAllReports() throws SQLException;

    public Collection<ReportEntity> getReportsCreatedBy(int userId) throws SQLException;

    public Collection<ReportEntity> getReportsFor(int userId) throws SQLException;

    public ReportEntity getReport(int reportId) throws SQLException;

    public Collection<ReportTypeVO> getReportTypes() throws SQLException;

    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) throws SQLException;

    public void updateReportStatus(int reportId, boolean status) throws SQLException;


}
