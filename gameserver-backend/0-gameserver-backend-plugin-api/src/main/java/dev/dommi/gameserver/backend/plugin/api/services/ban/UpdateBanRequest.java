package dev.dommi.gameserver.backend.plugin.api.services.ban;

import java.util.Date;

public class UpdateBanRequest {
    int id;
    public String reason;
    public Date until;
    public boolean active;

    public UpdateBanRequest() {
    }

    public UpdateBanRequest(int id, String reason, Date until, boolean active) {
        this.id = id;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }
}
