package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;


public class ReportUser {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public ReportUser(UserRepository userRepository, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        UserRankAggregate user = userRepository.findById(creatorId);
        if (user != null) {
            user.reportUser(reportedUserId, reason, reportTypeId, reportRepository);
        }
    }

}
