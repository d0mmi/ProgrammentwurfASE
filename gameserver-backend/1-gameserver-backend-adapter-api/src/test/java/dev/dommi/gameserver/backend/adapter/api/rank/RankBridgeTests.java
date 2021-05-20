package dev.dommi.gameserver.backend.adapter.api.rank;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RankBridgeTests {

    @Test
    public void invalidRankStringTest() {
        assertThrows(IllegalArgumentException.class, () -> new RankBridge(new RankRepositoryMock(), new UserRepositoryMock()).grantRankTo(1, "invalidRank"));
    }

    @Test
    public void convertToRankFromTest() {
        RankVO rankVO = new RankVO(1,"User", 1);
        Rank rank = RankBridge.convertToRankFrom(rankVO);
        assertNotNull(rank);
        assertEquals(rankVO.getName(), rank.name);
        assertEquals(rankVO.getLevel(), rank.level);
    }

    @Test
    public void convertToRankCollectionFromTest() {
        Collection<RankVO> vos = createVOs();
        Collection<Rank> ranks = RankBridge.convertToRankCollectionFrom(vos);

        for (int i = 0; i < vos.size(); i++) {
            Rank rank = (Rank) ranks.toArray()[i];
            RankVO rankVO = (RankVO) vos.toArray()[i];
            assertNotNull(rank);
            assertEquals(rankVO.getName(), rank.name);
            assertEquals(rankVO.getLevel(), rank.level);
        }

    }


    private Collection<RankVO> createVOs() {
        List<RankVO> vos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            vos.add(new RankVO(1,"User" + i, i));
        }
        return vos;
    }

}
