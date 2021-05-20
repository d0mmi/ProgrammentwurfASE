package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.aggregates.ReportAggregate;
import dev.dommi.gameserver.backend.domain.valueobjects.ReportTypeVO;

import java.util.Collection;

public interface ReportRepository {

    Collection<ReportAggregate> getAllReports();

    Collection<ReportAggregate> getReportsCreatedBy(int userId);

    Collection<ReportAggregate> getReportsFor(int userId);

    ReportAggregate getReport(int reportId);

    Collection<ReportTypeVO> getReportTypes();

    int getReportTypeIdByName(String name);

    boolean reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId);

    boolean update(ReportAggregate report);


}
