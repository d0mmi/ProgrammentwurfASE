package dev.dommi.gameserver.backend.plugin.database.rank;

public class UserRank {
    public int id;
    public int userId;
    public int rankId;

    public UserRank(int id, int userId, int rankId) {
        this.id = id;
        this.userId = userId;
        this.rankId = rankId;
    }

    public UserRank(int userId, int rankId) {
        this.id = -1;
        this.userId = userId;
        this.rankId = rankId;
    }
}
