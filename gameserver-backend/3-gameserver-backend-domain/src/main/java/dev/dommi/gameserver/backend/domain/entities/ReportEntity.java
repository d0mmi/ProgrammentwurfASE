package dev.dommi.gameserver.backend.domain.entities;


public class ReportEntity {
    private final int id;
    private final String reason;
    private boolean open;

    public ReportEntity(int id, String reason, boolean open) {
        if (id < 0 && id != -1) throw new IllegalArgumentException("id must be >= 0 or -1 to count as not set");
        this.id = id;
        if (!checkReason(reason)) throw new IllegalArgumentException("reason is invalid");
        this.reason = reason;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean updateStatus(boolean status) {
        if (this.open != status) {
            this.open = status;
            return true;
        }
        return false;
    }

    private boolean checkReason(String reason) {
        return reason != null && reason.length() > 0;
    }

}
