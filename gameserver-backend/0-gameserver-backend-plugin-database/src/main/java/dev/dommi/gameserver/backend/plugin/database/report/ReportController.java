package dev.dommi.gameserver.backend.plugin.database.report;

import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;

public class ReportController {

    private ReportTableWrapper reportTableWrapper;
    private ReportTypeTableWrapper reportTypeTableWrapper;

    public ReportController() {
        reportTableWrapper = new ReportTableWrapper(MariaDBConnector.getInstance().getConnection());
        reportTypeTableWrapper = new ReportTypeTableWrapper(MariaDBConnector.getInstance().getConnection());
    }


    public Collection<Report> getAllReports() throws SQLException {
        return reportTableWrapper.findAll();
    }


    public Collection<Report> getReportsCreatedBy(int userId) throws SQLException {
        return reportTableWrapper.findReportsCreatedBy(userId);
    }


    public Collection<Report> getReportsFor(int userId) throws SQLException {
        return reportTableWrapper.findReportsFor(userId);
    }


    public Report getReport(int reportId) throws SQLException {
        return reportTableWrapper.findById(reportId);
    }


    public Collection<ReportType> getReportTypes() throws SQLException {
        return reportTypeTableWrapper.findAll();
    }

    public ReportType getReportType(int id) throws SQLException {
        return reportTypeTableWrapper.findById(id);
    }


    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) throws SQLException {
        reportTableWrapper.create(new Report(creatorId, reportedUserId, reason, reportTypeId));
    }


    public void updateReportStatus(int reportId, boolean status) throws SQLException {
        reportTableWrapper.update(new Report(reportId, status));
    }

}
