package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.util.Collection;

public interface ReportDatabaseController extends DatabaseController<Report> {

    Collection<Report> getReportsCreatedBy(int userId);
    Collection<Report> getReportsFor(int userId);
    ReportType getReportType(int typeId);
    Collection<ReportType> getReportTypes();
    int getReportTypeIdByName(String name);
}
