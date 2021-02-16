package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.domain.entities.ReportEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ReportRepositoryImpl implements ReportRepository {

    private ReportDatabaseController controller;
    private UserRepository userRepository;

    public ReportRepositoryImpl(ReportDatabaseController controller, UserRepository userRepository) {
        this.controller = controller;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<ReportEntity> getAllReports() throws SQLException {
        return convertToReportEntityCollectionFrom(controller.findAll());
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
        return convertToReportEntityFrom(controller.findById(reportId));
    }

    @Override
    public Collection<ReportTypeVO> getReportTypes() throws SQLException {
        return convertToReportTypeEntityCollectionFrom(controller.getReportTypes());
    }

    @Override
    public int getReportTypeIdByName(String name) throws SQLException {
        return controller.getReportTypeIdByName(name);
    }

    @Override
    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) throws SQLException {
        controller.create(new Report(creatorId, reportedUserId, reason, reportTypeId));
    }

    @Override
    public void updateReportStatus(int reportId, boolean status) throws SQLException {
        controller.update(new Report(reportId,status));
    }


    ReportEntity convertToReportEntityFrom(Report report) throws SQLException {
        if (report == null) return null;
        ReportType type = controller.getReportType(report.typeId);
        UserEntity creator = userRepository.findById(report.creator);
        UserEntity reported = userRepository.findById(report.reported);
        return new ReportEntity(report.id, creator, reported, report.reason, convertToReportTypeEntityFrom(type), report.open);
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
        return new ReportTypeVO(type.id, type.name);
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
