package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.RankService;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    public RankServiceImpl(RankRepository rankRepository, UserRepository userRepository) {
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
    }

    public void grantRankTo(int userId, RankType rank) {
        UserRankAggregate user = userRepository.findById(userId);
        RankVO rankVO = rankRepository.getRankFrom(rank.value);
        if (user != null && rankVO != null) {
            grantRankTo(user, rankVO);
        }
    }

    public void grantRankTo(UserRankAggregate user, RankType rank) {
        RankVO rankVO = rankRepository.getRankFrom(rank.value);
        if (user != null && rankVO != null) {
            grantRankTo(user, rankVO);
        }
    }

    public void revokeRankFrom(int userId) {

        UserRankAggregate user = userRepository.findById(userId);
        if (user != null) {
            revokeRankFrom(user);
        }
    }

    @Override
    public boolean grantRankTo(UserRankAggregate user, RankVO rank) {
        user.grantRank(rank);
        return rankRepository.update(user);
    }

    @Override
    public boolean revokeRankFrom(UserRankAggregate user) {
        RankVO rank = rankRepository.getRankFrom(RankType.USER.value);
        user.grantRank(rank);
        return rankRepository.update(user);
    }
}
