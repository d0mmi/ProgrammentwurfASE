package dev.dommi.gameserver.backend.plugin.api.services.ban;

import java.util.Date;

public class BanUserRequest {
    public int userId;
    public String reason;
    public Date until;

    public BanUserRequest() {
    }

    public BanUserRequest(int userId, String reason, Date until) {
        this.userId = userId;
        this.reason = reason;
        this.until = until;
    }
}
