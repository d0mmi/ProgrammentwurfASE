package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;
import java.util.Collection;

public interface ReportDatabaseController extends DatabaseController<Report> {

    Collection<Report> getReportsCreatedBy(int userId) throws SQLException;
    Collection<Report> getReportsFor(int userId) throws SQLException;
    ReportType getReportType(int typeId) throws SQLException;
    Collection<ReportType> getReportTypes() throws SQLException;
    int getReportTypeIdByName(String name) throws SQLException;
}
