package dev.dommi.gameserver.backend.domain.mocks;

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
        return new RankVO(1,"test", 1);
    }

    @Override
    public int getRankIdFrom(String name) {
        return 1;
    }

    @Override
    public boolean grantRank(int userId, int rankId) {
        return  true;
    }

    @Override
    public boolean revokeRank(int userId, int rankId) {
        return  true;
    }

    @Override
    public boolean revokeAllRanks(int userId) {
        return  true;
    }
}
