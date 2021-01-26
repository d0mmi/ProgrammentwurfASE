package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RevokeRank {
    private static final Logger logger = Logger.getLogger(RevokeRank.class.getName());
    private RankRepository rankRepository;
    private UserRepository userRepository;

    public RevokeRank(RankRepository rankRepository, UserRepository userRepository) {
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
    }

    public void revokeRankFrom(int userId) {
        try {
            UserEntity user = userRepository.findById(userId);
            user.revokeRank(rankRepository);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

}
