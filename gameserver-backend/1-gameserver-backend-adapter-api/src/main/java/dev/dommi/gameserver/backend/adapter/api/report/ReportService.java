package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.application.report.GetReports;
import dev.dommi.gameserver.backend.application.report.ReportUser;
import dev.dommi.gameserver.backend.application.report.UpdateReportStatus;
import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.ReportTypeEntity;

import java.util.ArrayList;
import java.util.Collection;

public class ReportService {

    private ReportService() {
    }


    public static Collection<Report> getAllReports() {
        return convertToReportCollectionFrom(new GetReports().getAll());
    }


    public static Collection<Report> getReportsCreatedBy(int userId) {
        return convertToReportCollectionFrom(new GetReports().getReportsCreatedBy(userId));
    }


    public static Collection<Report> getReportsFor(int userId) {
        return convertToReportCollectionFrom(new GetReports().getReportsFor(userId));
    }


    public static Report getReport(int reportId) {
        return convertToReportFrom(new GetReports().getReport(reportId));
    }


    public static Collection<ReportType> getReportTypes() {
        return convertToReportTypeCollectionFrom(new GetReports().getReportTypes());
    }


    public static void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        new ReportUser().reportUser(creatorId, reportedUserId, reason, reportTypeId);
    }


    public static void updateReportStatus(int reportId, boolean status) {
        new UpdateReportStatus().updateReportStatus(reportId, status);
    }

    private static Report convertToReportFrom(ReportEntity report) {
        if (report == null) return null;
        ReportType type = report.type == null ? null : new ReportType(report.type.id, report.type.name);
        return new Report(report.id, report.creator, report.reported, report.reason, type, report.open);
    }

    private static Collection<Report> convertToReportCollectionFrom(Collection<ReportEntity> entities) {
        Collection<Report> reports = new ArrayList<>();
        for (ReportEntity entity : entities) {
            if (entity != null) {
                reports.add(convertToReportFrom(entity));
            }
        }
        return reports;
    }


    private static ReportType convertToReportTypeFrom(ReportTypeEntity type) {
        if (type == null) return null;
        return new ReportType(type.id, type.name);
    }

    private static Collection<ReportType> convertToReportTypeCollectionFrom(Collection<ReportTypeEntity> entities) {
        Collection<ReportType> types = new ArrayList<>();
        for (ReportTypeEntity entity : entities) {
            if (entity != null) {
                types.add(convertToReportTypeFrom(entity));
            }
        }
        return types;
    }

}
