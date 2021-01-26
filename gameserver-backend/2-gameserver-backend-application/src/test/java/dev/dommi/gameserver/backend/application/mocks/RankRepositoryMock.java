package dev.dommi.gameserver.backend.application.mocks;

import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class RankRepositoryMock implements RankRepository {
    @Override
    public Collection<RankVO> getAllRanks() throws SQLException {
        return Arrays.asList(new RankVO("User", 1));
    }

    @Override
    public RankVO getRankFrom(int userId) throws SQLException {
        return new RankVO("User", 1);
    }

    @Override
    public int getRankIdFrom(String name) throws SQLException {
        return 1;
    }

    @Override
    public void grantRank(int userId, int rankId) throws SQLException {

    }

    @Override
    public void revokeRank(int userId, int rankId) throws SQLException {

    }

    @Override
    public void revokeAllRanks(int userId) throws SQLException {

    }
}
