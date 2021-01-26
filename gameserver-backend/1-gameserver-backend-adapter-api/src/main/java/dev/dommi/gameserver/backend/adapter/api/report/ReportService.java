package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.application.report.GetReports;
import dev.dommi.gameserver.backend.application.report.ReportUser;
import dev.dommi.gameserver.backend.application.report.UpdateReportStatus;
import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.ReportTypeEntity;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ReportService {

    private final GetReports getReports;
    private final ReportUser reportUser;
    private final UpdateReportStatus updateReportStatus;

    public ReportService(ReportRepository reportRepository) {
        getReports = new GetReports(reportRepository);
        reportUser = new ReportUser(reportRepository);
        updateReportStatus = new UpdateReportStatus(reportRepository);
    }


    public Collection<Report> getAllReports() {
        return convertToReportCollectionFrom(getReports.getAll());
    }


    public Collection<Report> getReportsCreatedBy(int userId) {
        return convertToReportCollectionFrom(getReports.getReportsCreatedBy(userId));
    }


    public Collection<Report> getReportsFor(int userId) {
        return convertToReportCollectionFrom(getReports.getReportsFor(userId));
    }


    public Report getReport(int reportId) {
        return convertToReportFrom(getReports.getReport(reportId));
    }


    public Collection<ReportType> getReportTypes() {
        return convertToReportTypeCollectionFrom(getReports.getReportTypes());
    }


    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        reportUser.reportUser(creatorId, reportedUserId, reason, reportTypeId);
    }


    public void updateReportStatus(int reportId, boolean status) {
        updateReportStatus.updateReportStatus(reportId, status);
    }

    static Report convertToReportFrom(ReportEntity report) {
        if (report == null) return null;
        return new Report(report.getId(), report.getCreator(), report.getReported(), report.getReason(), convertToReportTypeFrom(report.getType()), report.isOpen());
    }

    static Collection<Report> convertToReportCollectionFrom(Collection<ReportEntity> entities) {
        Collection<Report> reports = new ArrayList<>();
        for (ReportEntity entity : entities) {
            if (entity != null) {
                reports.add(convertToReportFrom(entity));
            }
        }
        return reports;
    }


    static ReportType convertToReportTypeFrom(ReportTypeEntity type) {
        if (type == null) return null;
        return new ReportType(type.getId(), type.getName());
    }

    static Collection<ReportType> convertToReportTypeCollectionFrom(Collection<ReportTypeEntity> entities) {
        Collection<ReportType> types = new ArrayList<>();
        for (ReportTypeEntity entity : entities) {
            if (entity != null) {
                types.add(convertToReportTypeFrom(entity));
            }
        }
        return types;
    }

}
