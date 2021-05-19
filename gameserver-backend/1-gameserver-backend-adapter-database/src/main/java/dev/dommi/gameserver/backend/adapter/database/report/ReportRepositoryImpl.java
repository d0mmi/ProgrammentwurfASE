package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.adapter.database.user.UserDatabaseController;
import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ReportRepositoryImpl implements ReportRepository {

    private final ReportDatabaseController controller;
    private final UserDatabaseController userDatabaseController;

    public ReportRepositoryImpl(ReportDatabaseController controller, UserDatabaseController userDatabaseController) {
        this.controller = controller;
        this.userDatabaseController = userDatabaseController;
    }

    @Override
    public Collection<ReportAggregate> getAllReports() {
        return convertCollection(controller.findAll());
    }

    @Override
    public Collection<ReportAggregate> getReportsCreatedBy(int userId) {
        return convertCollection(controller.getReportsCreatedBy(userId));
    }

    @Override
    public Collection<ReportAggregate> getReportsFor(int userId) {
        return convertCollection(controller.getReportsFor(userId));
    }

    @Override
    public ReportAggregate getReport(int reportId) {
        Report report = controller.findById(reportId);
        ReportType type = controller.getReportType(report.typeId);
        User creator = userDatabaseController.findById(report.creator);
        User reported = userDatabaseController.findById(report.reported);
        return ReportMapper.getReportAggregateFrom(report, type, creator, reported);
    }

    @Override
    public Collection<ReportTypeVO> getReportTypes() {
        return ReportMapper.getReportTypeVOCollectionFrom(controller.getReportTypes());
    }

    @Override
    public int getReportTypeIdByName(String name) {
        return controller.getReportTypeIdByName(name);
    }

    @Override
    public boolean reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        return controller.create(new Report(creatorId, reportedUserId, reason, reportTypeId));
    }

    @Override
    public boolean updateReportStatus(int reportId, boolean status) {
        return controller.update(new Report(reportId, status));
    }


    Collection<ReportAggregate> convertCollection(Collection<Report> reports) {
        Collection<ReportAggregate> entities = new ArrayList<>();
        for (Report report : reports) {
            if (report != null) {
                ReportType type = controller.getReportType(report.typeId);
                User creator = userDatabaseController.findById(report.creator);
                User reported = userDatabaseController.findById(report.reported);
                entities.add(ReportMapper.getReportAggregateFrom(report, type, creator, reported));
            }
        }
        return entities;
    }

}
