package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;

public interface RankDatabaseController extends DatabaseController<Rank> {

    Rank getRankFrom(int userId) throws SQLException;
    int getRankIdFrom(String name) throws SQLException;
    void grantRank(int userId, int rankId) throws SQLException;
    void revokeRank(int userId, int rankId) throws SQLException;
    void revokeAllRanks(int userId) throws SQLException;

}
