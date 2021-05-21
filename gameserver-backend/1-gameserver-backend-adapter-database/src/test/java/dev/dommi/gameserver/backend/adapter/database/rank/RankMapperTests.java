package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RankMapperTests {
    @Test
    public void convertToRankVOFromTest() {
        Rank rank = new Rank("exampleRank", 1);
        RankVO rankVO = RankMapper.getRankVOFrom(rank);

        assertNotNull(rank);
        assertEquals(rankVO.getName(), rank.name);
        assertEquals(rankVO.getLevel(), rank.level);
    }

    @Test
    public void convertToRankVOCollectionFromTest() {
        Collection<Rank> ranks = createRanks();
        Collection<RankVO> vos =  RankMapper.getUserEntityCollectionFrom(ranks);

        for (int i = 0; i < ranks.size(); i++) {
            Rank rank = (Rank) ranks.toArray()[i];
            RankVO rankVO = (RankVO) vos.toArray()[i];

            assertNotNull(rankVO);
            assertEquals(rank.name, rankVO.getName());
            assertEquals(rank.level, rankVO.getLevel());
        }
    }

    private Collection<Rank> createRanks() {
        List<Rank> values = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            values.add(new Rank("Rank" + i, i));
        }
        return values;
    }
}
