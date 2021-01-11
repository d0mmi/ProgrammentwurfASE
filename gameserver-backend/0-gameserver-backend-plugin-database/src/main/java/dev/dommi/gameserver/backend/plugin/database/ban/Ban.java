package dev.dommi.gameserver.backend.plugin.database.ban;

import java.util.Date;

public class Ban {
    public int id;
    public int userId;
    public int bannedById;
    public String reason;
    public Date until;
    public boolean active;

    public Ban(int id, int userId, int bannedById, String reason, Date until, boolean active) {
        this.id = id;
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }

    public Ban(int id, int userId, int bannedById, String reason, Date until) {
        this.id = id;
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = true;
    }

    public Ban(int userId, int bannedById, String reason, Date until) {
        this.id = -1;
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = true;
    }

    public Ban(int id, String reason, Date until, boolean active) {
        this.id = id;
        this.userId = -1;
        this.bannedById = -1;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }
}
