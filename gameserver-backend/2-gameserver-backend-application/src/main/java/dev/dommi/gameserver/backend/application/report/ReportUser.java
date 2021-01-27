package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ReportUser {
    private static final Logger logger = Logger.getLogger(ReportUser.class.getName());
    private ReportRepository repository;

    public ReportUser(ReportRepository repository) {
        this.repository = repository;
    }

    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        try {
            repository.reportUser(creatorId, reportedUserId, reason, reportTypeId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
