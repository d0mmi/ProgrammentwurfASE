package dev.dommi.gameserver.backend.application.mocks;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class RankRepositoryMock implements RankRepository {
    @Override
    public Collection<RankVO> getAllRanks() {
        return Arrays.asList(new RankVO(1, "User", 1));
    }

    @Override
    public RankVO getRankFrom(int userId) {
        return new RankVO(1, "User", 1);
    }

    @Override
    public RankVO getRankFrom(String name) {
        return new RankVO(1,name,0);
    }

    @Override
    public boolean update(UserRankAggregate user) {
        return true;
    }
}
