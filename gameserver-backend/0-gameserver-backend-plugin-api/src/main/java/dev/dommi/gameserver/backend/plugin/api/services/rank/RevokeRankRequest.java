package dev.dommi.gameserver.backend.plugin.api.services.rank;

public class RevokeRankRequest {
    public int userId;

    public RevokeRankRequest() {
    }

    public RevokeRankRequest(int userId) {
        this.userId = userId;
    }
}
