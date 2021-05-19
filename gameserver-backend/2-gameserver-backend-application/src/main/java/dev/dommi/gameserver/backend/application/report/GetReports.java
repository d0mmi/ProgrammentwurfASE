package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

import java.util.ArrayList;
import java.util.Collection;

public class GetReports {
    private final ReportRepository repository;

    public GetReports(ReportRepository repository) {
        this.repository = repository;
    }

    public Collection<ReportAggregate> getAll() {
        return repository.getAllReports();
    }

    public Collection<ReportAggregate> getReportsCreatedBy(int userId) {
        return repository.getReportsCreatedBy(userId);
    }

    public Collection<ReportAggregate> getReportsFor(int userId) {
        return repository.getReportsFor(userId);
    }

    public ReportAggregate getReport(int reportId) {
        return repository.getReport(reportId);
    }


    public Collection<ReportTypeVO> getReportTypes() {
        return repository.getReportTypes();
    }

    public int getReportTypeIdByName(String name) {
        return repository.getReportTypeIdByName(name);
    }
}
