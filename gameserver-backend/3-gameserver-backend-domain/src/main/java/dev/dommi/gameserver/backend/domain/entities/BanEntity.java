package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.sql.SQLException;
import java.util.Date;

public class BanEntity {
    private int id;
    private UserEntity user;
    private UserEntity bannedBy;
    private String reason;
    private Date until;
    private boolean active;

    public BanEntity(int id, UserEntity user, UserEntity bannedBy, String reason, Date until, boolean active) {
        this.id = id;
        this.user = user;
        this.bannedBy = bannedBy;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserEntity getBannedBy() {
        return bannedBy;
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
