package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;

public interface RankDatabaseController extends DatabaseController<Rank> {

    public Rank getRankFrom(int userId) throws SQLException;
    public int getRankIdFrom(String name) throws SQLException;
    public void grantRank(int userId, int rankId) throws SQLException;
    public void revokeRank(int userId, int rankId) throws SQLException;
    public void revokeAllRanks(int userId) throws SQLException;

}
