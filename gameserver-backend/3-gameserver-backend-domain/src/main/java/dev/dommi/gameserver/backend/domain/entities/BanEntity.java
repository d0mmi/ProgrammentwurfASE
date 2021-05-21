package dev.dommi.gameserver.backend.domain.entities;


import java.util.Date;

public class BanEntity {
    private final int id;
    private String reason;
    private Date until;
    private boolean active;

    public BanEntity(int id, String reason, Date until, boolean active) {
        if (id < 0 && id != -1) throw new IllegalArgumentException("id must be >= 0 or -1 to count as not set");
        this.id = id;
        if (!checkReason(reason)) throw new IllegalArgumentException("reason is invalid");
        this.reason = reason;
        if (!checkUntil(until)) throw new IllegalArgumentException("until is invalid");
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
        if (checkReason(reason)) {
            this.reason = reason;
            updated = true;
        }
        if (checkUntil(until)) {
            this.until = until;
            updated = true;
        }
        if (this.active != active) {
            this.active = active;
            updated = true;
        }
        return updated;
    }

    private boolean checkReason(String reason) {
        return reason != null && reason.length() > 0;
    }

    private boolean checkUntil(Date until) {
        return until != null;
    }
}
