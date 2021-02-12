package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Collection;

public interface RankRepository {

    Collection<RankVO> getAllRanks() throws SQLException;

    RankVO getRankFrom(int userId) throws SQLException;

    int getRankIdFrom(String name) throws SQLException;

    void grantRank(int userId, int rankId) throws SQLException;

    void revokeRank(int userId, int rankId) throws SQLException;

    void revokeAllRanks(int userId) throws SQLException;

}
