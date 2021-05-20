package dev.dommi.gameserver.backend.adapter.api.rank;

import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Collection;

public class RankRepositoryMock implements RankRepository {
    @Override
    public Collection<RankVO> getAllRanks() {
        return null;
    }

    @Override
    public RankVO getRankFrom(int userId) {
        return null;
    }

    @Override
    public int getRankIdFrom(String name) {
        return 0;
    }

    @Override
    public boolean grantRank(int userId, int rankId) {
        return true;
    }

    @Override
    public boolean revokeRank(int userId, int rankId) {
        return true;
    }

    @Override
    public boolean revokeAllRanks(int userId) {
        return true;
    }
}
