package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.sql.SQLException;
import java.util.logging.Logger;

public class GrantRank {
    private static final Logger logger = Logger.getLogger(GrantRank.class.getName());
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    public GrantRank(RankRepository repository, UserRepository userRepository) {
        this.rankRepository = repository;
        this.userRepository = userRepository;
    }

    public void grantRankTo(int userId, RankType rank) {
        UserRankAggregate user = userRepository.findById(userId);
        if (user != null) {
            if (!user.getRankName().equalsIgnoreCase(rank.value)) {
                user.grantRank(rank, rankRepository);
            }
        }
    }

}
