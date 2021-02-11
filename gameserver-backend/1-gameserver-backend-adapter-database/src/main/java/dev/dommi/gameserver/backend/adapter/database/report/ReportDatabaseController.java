package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;
import java.util.Collection;

public interface ReportDatabaseController extends DatabaseController<Report> {

    public Collection<Report> getReportsCreatedBy(int userId) throws SQLException;
    public Collection<Report> getReportsFor(int userId) throws SQLException;
    public ReportType getReportType(int typeId) throws SQLException;
    public Collection<ReportType> getReportTypes() throws SQLException;
    public int getReportTypeIdByName(String name) throws SQLException;
}
