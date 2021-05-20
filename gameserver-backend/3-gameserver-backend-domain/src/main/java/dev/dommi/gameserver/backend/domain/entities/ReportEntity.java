package dev.dommi.gameserver.backend.domain.entities;


public class ReportEntity {
    private final int id;
    private final String reason;
    private boolean open;

    public ReportEntity(int id, String reason, boolean open) {
        this.id = id;
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

}
