package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.sql.SQLException;
import java.util.Date;

public class BanEntity {
    private int id;
    private int userId;
    private int bannedById;
    private String reason;
    private Date until;
    private boolean active;

    public BanEntity(int id, int userId, int bannedById, String reason, Date until, boolean active) {
        this.id = id;
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBannedById() {
        return bannedById;
    }

    public String getReason() {
        return reason;
    }

    public Date getUntil() {
        return until;
    }

    public boolean isActive() {
        return active;
    }

    public BanEntity update(String reason, Date until, boolean active, BanRepository repository) throws SQLException {
        if (reason != null) this.reason = reason;
        if (until != null) this.until = until;
        this.active = active;
        repository.update(id, reason, until, active);
        return this;
    }
}
