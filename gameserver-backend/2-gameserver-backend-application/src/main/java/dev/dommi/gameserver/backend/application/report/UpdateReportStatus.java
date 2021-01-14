package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.adapter.database.report.ReportRepository;
import dev.dommi.gameserver.backend.adapter.database.report.ReportRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UpdateReportStatus {
    private static final Logger logger = Logger.getLogger(UpdateReportStatus.class.getName());
    private ReportRepository repository;

    public UpdateReportStatus(ReportRepository repository) {
        this.repository = repository;
    }

    public UpdateReportStatus() {
        repository = new ReportRepositoryImpl();
    }


    public void updateReportStatus(int reportId, boolean status) {
        try {
            repository.updateReportStatus(reportId, status);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
