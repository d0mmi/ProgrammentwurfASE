package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.sql.SQLException;
import java.util.logging.Logger;

public class GrantRank {
    private static final Logger logger = Logger.getLogger(GrantRank.class.getName());
    private RankRepository rankRepository;
    private UserRepository userRepository;

    public GrantRank(RankRepository repository, UserRepository userRepository) {
        this.rankRepository = repository;
        this.userRepository = userRepository;
    }

    public void grantRankTo(int userId, RankType rank) {
        try {
            int rankId = rankRepository.getRankIdFrom(rank.value);
            UserEntity user = userRepository.findById(userId);
            if (!user.getRank().getName().equalsIgnoreCase(rank.value)) {
                user.grantRank(rankId, rankRepository);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
