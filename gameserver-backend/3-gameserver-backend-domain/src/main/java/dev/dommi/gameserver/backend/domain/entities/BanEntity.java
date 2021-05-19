package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.util.Date;

public class BanEntity {
    private int id;
    private String reason;
    private Date until;
    private boolean active;

    public BanEntity(int id, String reason, Date until, boolean active) {
        this.id = id;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }

    public int getId() {
        return id;
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

    public boolean update(String reason, Date until, boolean active, BanRepository repository) {
        if(repository.update(id, reason, until, active)){
            if (reason != null) this.reason = reason;
            if (until != null) this.until = until;
            this.active = active;
            return true;
        }
        return false;
    }
}
