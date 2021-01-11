package dev.dommi.gameserver.backend.domain.entities;

import java.util.Date;

public class BanEntity {
    public int id;
    public int userId;
    public int bannedById;
    public String reason;
    public Date until;
    public boolean active;

    public BanEntity(int id, int userId, int bannedById, String reason, Date until, boolean active) {
        this.id = id;
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }
}
