package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Collection;

public interface RankRepository {

    public Collection<RankVO> getAllRanks() throws SQLException;

    public RankVO getRankFrom(int userId) throws SQLException;

    public int getRankIdFrom(String name) throws SQLException;

    public void grantRank(int userId, int rankId) throws SQLException;

    public void revokeRank(int userId, int rankId) throws SQLException;

    public void revokeAllRanks(int userId) throws SQLException;

}
