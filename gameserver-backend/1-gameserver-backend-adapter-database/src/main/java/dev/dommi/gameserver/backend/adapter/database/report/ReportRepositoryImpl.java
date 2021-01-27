package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.plugin.database.report.Report;
import dev.dommi.gameserver.backend.plugin.database.report.ReportController;
import dev.dommi.gameserver.backend.plugin.database.report.ReportType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ReportRepositoryImpl implements ReportRepository {

    private ReportController controller;

    public ReportRepositoryImpl(ReportController controller) {
        this.controller = controller;
    }

    public ReportRepositoryImpl() {
        controller = new ReportController();
    }

    @Override
    public Collection<ReportEntity> getAllReports() throws SQLException {
        return convertToReportEntityCollectionFrom(controller.getAllReports());
    }

    @Override
    public Collection<ReportEntity> getReportsCreatedBy(int userId) throws SQLException {
        return convertToReportEntityCollectionFrom(controller.getReportsCreatedBy(userId));
    }

    @Override
    public Collection<ReportEntity> getReportsFor(int userId) throws SQLException {
        return convertToReportEntityCollectionFrom(controller.getReportsFor(userId));
    }

    @Override
    public ReportEntity getReport(int reportId) throws SQLException {
        return convertToReportEntityFrom(controller.getReport(reportId));
    }

    @Override
    public Collection<ReportTypeVO> getReportTypes() throws SQLException {
        return convertToReportTypeEntityCollectionFrom(controller.getReportTypes());
    }

    @Override
    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) throws SQLException {
        controller.reportUser(creatorId, reportedUserId, reason, reportTypeId);
    }

    @Override
    public void updateReportStatus(int reportId, boolean status) throws SQLException {
        controller.updateReportStatus(reportId, status);
    }


    ReportEntity convertToReportEntityFrom(Report report) throws SQLException {
        if (report == null) return null;
        ReportType type = controller.getReportType(report.typeId);
        return new ReportEntity(report.id, report.creator, report.reported, report.reason, convertToReportTypeEntityFrom(type), report.open);
    }

    Collection<ReportEntity> convertToReportEntityCollectionFrom(Collection<Report> reports) throws SQLException {
        Collection<ReportEntity> entities = new ArrayList<>();
        for (Report report : reports) {
            if (report != null) {
                entities.add(convertToReportEntityFrom(report));
            }
        }
        return entities;
    }


    static ReportTypeVO convertToReportTypeEntityFrom(ReportType type) {
        if (type == null) return null;
        return new ReportTypeVO(type.name);
    }

    static Collection<ReportTypeVO> convertToReportTypeEntityCollectionFrom(Collection<ReportType> types) {
        Collection<ReportTypeVO> entities = new ArrayList<>();
        for (ReportType type : types) {
            if (type != null) {
                entities.add(convertToReportTypeEntityFrom(type));
            }
        }
        return entities;
    }

}
