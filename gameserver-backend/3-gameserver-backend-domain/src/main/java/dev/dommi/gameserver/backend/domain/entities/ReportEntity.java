package dev.dommi.gameserver.backend.domain.entities;

public class ReportEntity {
    public int id;
    public int creator;
    public int reported;
    public String reason;
    public ReportTypeEntity type;
    public boolean open;

    public ReportEntity(int id, int creator, int reported, String reason, ReportTypeEntity type, boolean open) {
        this.id = id;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.type = type;
        this.open = open;
    }
}
