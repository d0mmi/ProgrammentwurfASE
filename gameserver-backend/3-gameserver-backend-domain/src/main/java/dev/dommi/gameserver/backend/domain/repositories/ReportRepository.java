package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

import java.sql.SQLException;
import java.util.Collection;

public interface ReportRepository {

    Collection<ReportEntity> getAllReports() throws SQLException;

    Collection<ReportEntity> getReportsCreatedBy(int userId) throws SQLException;

    Collection<ReportEntity> getReportsFor(int userId) throws SQLException;

    ReportEntity getReport(int reportId) throws SQLException;

    Collection<ReportTypeVO> getReportTypes() throws SQLException;

    int getReportTypeIdByName(String name) throws SQLException;

    void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) throws SQLException;

    void updateReportStatus(int reportId, boolean status) throws SQLException;


}
