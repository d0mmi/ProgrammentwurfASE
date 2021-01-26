package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.adapter.database.report.ReportRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UpdateReportStatus {
    private static final Logger logger = Logger.getLogger(UpdateReportStatus.class.getName());
    private ReportRepository repository;

    public UpdateReportStatus(ReportRepository repository) {
        this.repository = repository;
    }


    public void updateReportStatus(int reportId, boolean status) {
        try {
            ReportEntity report = repository.getReport(reportId);
            report.updateStatus(status, repository);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
