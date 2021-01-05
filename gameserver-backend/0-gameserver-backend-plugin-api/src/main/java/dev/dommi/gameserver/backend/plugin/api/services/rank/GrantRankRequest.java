package dev.dommi.gameserver.backend.plugin.api.services.rank;

public class GrantRankRequest {
    public int userId;
    public String rank;

    public GrantRankRequest() {
    }

    public GrantRankRequest(int userId, String rank) {
        this.userId = userId;
        this.rank = rank;
    }

}
