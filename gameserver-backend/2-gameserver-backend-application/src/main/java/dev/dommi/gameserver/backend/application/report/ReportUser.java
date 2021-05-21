package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.ReportService;


public class ReportUser implements ReportService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public ReportUser(UserRepository userRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        UserRankAggregate creator = userRepository.findById(creatorId);
        UserRankAggregate reported = userRepository.findById(reportedUserId);
        if (creator != null && reported != null && reason != null && reason.length() > 0) {
            report(creator, reported, reason, reportTypeId);
        }
    }

    @Override
    public void report(UserRankAggregate creator, UserRankAggregate reported, String reason, int typeId) {
        reportRepository.create(creator.getUserId(), reported.getUserId(), reason, typeId);
    }
}
