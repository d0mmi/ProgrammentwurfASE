package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;


public class UpdateReportStatus {
    private final ReportRepository repository;

    public UpdateReportStatus(ReportRepository repository) {
        this.repository = repository;
    }


    public void updateReportStatus(int reportId, boolean status) {
        ReportAggregate report = repository.getReport(reportId);
        if (report != null) {
            report.updateStatus(status, repository);
        }
    }

}
