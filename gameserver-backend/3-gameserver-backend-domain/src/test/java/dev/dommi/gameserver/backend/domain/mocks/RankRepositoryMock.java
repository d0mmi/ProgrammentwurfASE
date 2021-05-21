package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.Collection;

public class RankRepositoryMock implements RankRepository {
    @Override
    public Collection<RankVO> getAllRanks() {
        return null;
    }

    @Override
    public RankVO getRankFrom(int userId) {
        return new RankVO(1, "test", 1);
    }

    @Override
    public RankVO getRankFrom(String name) {
        return new RankVO(1, name, 0);
    }

    @Override
    public boolean update(UserRankAggregate user) {
        return true;
    }
}
