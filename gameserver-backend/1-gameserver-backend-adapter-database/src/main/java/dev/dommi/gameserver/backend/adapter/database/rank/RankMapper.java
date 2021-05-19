package dev.dommi.gameserver.backend.adapter.database.rank;


import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.ArrayList;
import java.util.Collection;

public class RankMapper {

    public static RankVO getRankVOFrom(Rank rank) {
        if(rank == null) return  null;
        return new RankVO(rank.id, rank.name, rank.level);
    }

    public static Collection<RankVO> getUserEntityCollectionFrom(Collection<Rank> ranks) {
        Collection<RankVO> vos = new ArrayList<>();
        for (Rank rank : ranks) {
            if (rank != null) {
                vos.add(getRankVOFrom(rank));
            }
        }
        return vos;
    }

}
