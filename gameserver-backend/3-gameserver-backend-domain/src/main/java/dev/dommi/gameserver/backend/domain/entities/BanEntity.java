package dev.dommi.gameserver.backend.domain.entities;


import java.util.Date;

public class BanEntity {
    private final int id;
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

    public boolean update(String reason, Date until, boolean active) {
        boolean updated = false;
        if (reason != null) {
            this.reason = reason;
            updated = true;
        }
        if (until != null) {
            this.until = until;
            updated = true;
        }
        if (this.active != active) {
            this.active = active;
            updated = true;
        }
        return updated;
    }
}
