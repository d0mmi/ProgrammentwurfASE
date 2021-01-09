package dev.dommi.gameserver.backend.application.report;

import dev.dommi.gameserver.backend.adapter.database.report.ReportRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ReportUser {
    private static final Logger logger = Logger.getLogger(ReportUser.class.getName());

    public void reportUser(int creatorId, int reportedUserId, String reason, int reportTypeId) {
        try {
            new ReportRepositoryImpl().reportUser(creatorId, reportedUserId, reason, reportTypeId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
