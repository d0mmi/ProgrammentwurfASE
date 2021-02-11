package dev.dommi.gameserver.backend.plugin.database.report;

import dev.dommi.gameserver.backend.adapter.database.report.Report;
import dev.dommi.gameserver.backend.adapter.database.report.ReportDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.report.ReportType;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;

public class ReportDatabaseControllerImpl implements ReportDatabaseController {

    private ReportTableWrapper reportTableWrapper;
    private ReportTypeTableWrapper reportTypeTableWrapper;

    public ReportDatabaseControllerImpl() {
        reportTableWrapper = new ReportTableWrapper(MariaDBConnector.getInstance());
        reportTypeTableWrapper = new ReportTypeTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public Collection<Report> findAll() throws SQLException {
        return reportTableWrapper.findAll();
    }


    @Override
    public Collection<Report> getReportsCreatedBy(int userId) throws SQLException {
        return reportTableWrapper.findReportsCreatedBy(userId);
    }


    @Override
    public Collection<Report> getReportsFor(int userId) throws SQLException {
        return reportTableWrapper.findReportsFor(userId);
    }


    @Override
    public Report findById(int reportId) throws SQLException {
        return reportTableWrapper.findById(reportId);
    }


    @Override
    public Collection<ReportType> getReportTypes() throws SQLException {
        return reportTypeTableWrapper.findAll();
    }

    @Override
    public ReportType getReportType(int id) throws SQLException {
        return reportTypeTableWrapper.findById(id);
    }

    @Override
    public int getReportTypeIdByName(String name) throws SQLException {
        return reportTypeTableWrapper.findIdByName(name);
    }


    @Override
    public void update(Report value) throws SQLException {
        reportTableWrapper.update(value);
    }

    @Override
    public void create(Report value) throws SQLException {
        reportTableWrapper.create(value);
    }


    @Override
    public void delete(int value) throws SQLException {
        reportTableWrapper.deleteById(value);
    }


}
