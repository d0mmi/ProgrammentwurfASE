package dev.dommi.gameserver.backend.adapter.api.rank;


import dev.dommi.gameserver.backend.application.rank.GetAllRanks;
import dev.dommi.gameserver.backend.application.rank.RankServiceImpl;
import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.ArrayList;
import java.util.Collection;

public class RankBridge {

    private final RankServiceImpl rankService;
    private final GetAllRanks getAllRanks;

    public RankBridge(RankRepository rankRepository, UserRepository userRepository) {
        rankService = new RankServiceImpl(rankRepository, userRepository);
        getAllRanks = new GetAllRanks(rankRepository);
    }

    public void grantRankTo(int userId, String rank) throws IllegalArgumentException {
        RankType type = RankType.valueOf(rank.toUpperCase());
        rankService.grantRankTo(userId, type);
    }

    public void revokeRankFrom(int userId) {
        rankService.revokeRankFrom(userId);
    }

    public Collection<Rank> getAll() {
        return convertToRankCollectionFrom(getAllRanks.getAll());
    }

    static Rank convertToRankFrom(RankVO rankVO) {
        if (rankVO == null) return null;
        return new Rank(rankVO.getId(), rankVO.getName(), rankVO.getLevel());
    }

    static Collection<Rank> convertToRankCollectionFrom(Collection<RankVO> rankVOs) {
        Collection<Rank> ranks = new ArrayList<>();
        for (RankVO rankVO : rankVOs) {
            if (rankVO != null) {
                ranks.add(convertToRankFrom(rankVO));
            }
        }
        return ranks;
    }

}
