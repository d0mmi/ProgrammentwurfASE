package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.RankRepository;
import dev.dommi.gameserver.backend.adapter.database.rank.RankRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class GrantRank {
    private static final Logger logger = Logger.getLogger(GrantRank.class.getName());
    private RankRepository repository;

    public GrantRank(RankRepository repository) {
        this.repository = repository;
    }

    public GrantRank() {
        repository = new RankRepositoryImpl();
    }

    public void grantRankTo(int userId, RankType rank) {
        try {
            int rankId = repository.getRankIdFrom(rank.value);
            if (!repository.getRankFrom(userId).name.equalsIgnoreCase(rank.value)) {
                repository.revokeAllRanks(userId);
                repository.grantRank(userId, rankId);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
