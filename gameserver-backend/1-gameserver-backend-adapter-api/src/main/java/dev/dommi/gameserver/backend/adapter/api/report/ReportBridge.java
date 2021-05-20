package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.adapter.api.user.UserBridge;
import dev.dommi.gameserver.backend.application.report.GetReports;
import dev.dommi.gameserver.backend.application.report.ReportUser;
import dev.dommi.gameserver.backend.application.report.UpdateReportStatus;
import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ReportBridge {

    private final GetReports getReports;
    private final ReportUser reportUser;
    private final UpdateReportStatus updateReportStatus;

    public ReportBridge(UserRepository userRepository, ReportRepository reportRepository) {
        getReports = new GetReports(reportRepository);
        reportUser = new ReportUser(userRepository, reportRepository);
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

    public int getReportTypeIdByName(String name) {
        return getReports.getReportTypeIdByName(name);
    }


    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        reportUser.reportUser(creatorId, reportedUserId, reason, reportTypeId);
    }


    public void updateReportStatus(int reportId, boolean status) {
        updateReportStatus.updateReportStatus(reportId, status);
    }

    static Report convertToReportFrom(ReportAggregate report) {
        if (report == null) return null;
        return new Report(report.getReportId(), UserBridge.convertToUserFrom(report.getCreator()), UserBridge.convertToUserFrom(report.getReported()), report.getReason(), convertToReportTypeFrom(report.getReportType()), report.isOpen());
    }

    static Collection<Report> convertToReportCollectionFrom(Collection<ReportAggregate> entities) {
        Collection<Report> reports = new ArrayList<>();
        for (ReportAggregate entity : entities) {
            if (entity != null) {
                reports.add(convertToReportFrom(entity));
            }
        }
        return reports;
    }


    static ReportType convertToReportTypeFrom(ReportTypeVO type) {
        if (type == null) return null;
        return new ReportType(type.getId(), type.getName());
    }

    static Collection<ReportType> convertToReportTypeCollectionFrom(Collection<ReportTypeVO> entities) {
        Collection<ReportType> types = new ArrayList<>();
        for (ReportTypeVO entity : entities) {
            if (entity != null) {
                types.add(convertToReportTypeFrom(entity));
            }
        }
        return types;
    }

}
