package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.RankRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RevokeRank {
    private static final Logger logger = Logger.getLogger(RevokeRank.class.getName());

    public void revokeRankFrom(int userId) {
        RankRepositoryImpl repository = new RankRepositoryImpl();
        try {
            int rankId = repository.getRankIdFrom(RankType.USER.value);
            if (!repository.getRankFrom(userId).name.equalsIgnoreCase(RankType.USER.value)) {
                repository.revokeAllRanks(userId);
                repository.grantRank(userId, rankId);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
