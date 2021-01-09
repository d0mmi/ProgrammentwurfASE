package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.adapter.database.report.ReportRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.ReportTypeEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class GetReports {
    private static final Logger logger = Logger.getLogger(GetReports.class.getName());

    public Collection<ReportEntity> getAll() {
        try {
            return new ReportRepositoryImpl().getAllReports();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<ReportEntity> getReportsCreatedBy(int userId) {
        try {
            return new ReportRepositoryImpl().getReportsCreatedBy(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<ReportEntity> getReportsFor(int userId) {
        try {
            return new ReportRepositoryImpl().getReportsFor(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public ReportEntity getReport(int reportId) {
        try {
            return new ReportRepositoryImpl().getReport(reportId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }


    public Collection<ReportTypeEntity> getReportTypes() {
        try {
            return new ReportRepositoryImpl().getReportTypes();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }
}
