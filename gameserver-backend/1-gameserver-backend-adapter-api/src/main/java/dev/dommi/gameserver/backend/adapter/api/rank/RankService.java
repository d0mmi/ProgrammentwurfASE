package dev.dommi.gameserver.backend.adapter.api.rank;


import dev.dommi.gameserver.backend.application.rank.GetAllRanks;
import dev.dommi.gameserver.backend.application.rank.GrantRank;
import dev.dommi.gameserver.backend.domain.entities.RankType;
import dev.dommi.gameserver.backend.application.rank.RevokeRank;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class RankService {

    private static final Logger logger = Logger.getLogger(RankService.class.getName());
    private final GrantRank grantRank;
    private final RevokeRank revokeRank;
    private final GetAllRanks getAllRanks;

    public RankService(RankRepository rankRepository, UserRepository userRepository) {
        grantRank = new GrantRank(rankRepository, userRepository);
        revokeRank = new RevokeRank(rankRepository, userRepository);
        getAllRanks = new GetAllRanks(rankRepository);
    }

    public void grantRankTo(int userId, String rank) throws IllegalArgumentException {
        RankType type = RankType.valueOf(rank.toUpperCase());
        grantRank.grantRankTo(userId, type);
    }

    public void revokeRankFrom(int userId) {
        revokeRank.revokeRankFrom(userId);
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
