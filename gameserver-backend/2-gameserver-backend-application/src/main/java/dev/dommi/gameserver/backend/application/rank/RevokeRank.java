package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

public class RevokeRank {
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    public RevokeRank(RankRepository rankRepository, UserRepository userRepository) {
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
    }

    public void revokeRankFrom(int userId) {

        UserRankAggregate user = userRepository.findById(userId);
        if (user != null) {
            user.revokeRank(rankRepository);
        }
    }

}
