package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;


public interface RankDatabaseController extends DatabaseController<Rank> {

    Rank getRankFrom(int userId);

    Rank getRankFrom(String name);

    boolean update(int userId, int rankId);

}
