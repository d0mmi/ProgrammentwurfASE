package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;


public interface RankDatabaseController extends DatabaseController<Rank> {

    Rank getRankFrom(int userId);
    int getRankIdFrom(String name);
    boolean grantRank(int userId, int rankId);
    boolean revokeRank(int userId, int rankId);
    boolean revokeAllRanks(int userId);

}
