package dev.dommi.gameserver.backend.adapter.api.rank;


import dev.dommi.gameserver.backend.application.rank.GetAllRanks;
import dev.dommi.gameserver.backend.application.rank.GrantRank;
import dev.dommi.gameserver.backend.application.rank.RankType;
import dev.dommi.gameserver.backend.application.rank.RevokeRank;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class RankService {

    private static final Logger logger = Logger.getLogger(RankService.class.getName());

    private RankService() {
    }

    public static void grantRankTo(int userId, String rank) throws IllegalArgumentException {
        RankType type = RankType.valueOf(rank.toUpperCase());
        new GrantRank().grantRankTo(userId, type);
    }

    public static void revokeRankFrom(int userId) {
        new RevokeRank().revokeRankFrom(userId);
    }

    public static Collection<Rank> getAll() {
        return convertToRankCollectionFrom(new GetAllRanks().getAll());
    }

    private static Rank convertToRankFrom(RankVO rankVO) {
        if (rankVO == null) return null;
        return new Rank(rankVO.name, rankVO.level);
    }

    private static Collection<Rank> convertToRankCollectionFrom(Collection<RankVO> rankVOs) {
        Collection<Rank> ranks = new ArrayList<>();
        for (RankVO rankVO : rankVOs) {
            if (rankVO != null) {
                ranks.add(convertToRankFrom(rankVO));
            }
        }
        return ranks;
    }

}
