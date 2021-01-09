package dev.dommi.gameserver.backend.plugin.database.report;

public class Report {
    public int id;
    public int creator;
    public int reported;
    public String reason;
    public int typeId;
    public boolean open;

    public Report(int id, int creator, int reported, String reason, int typeId, boolean open) {
        this.id = id;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.typeId = typeId;
        this.open = open;
    }

    public Report(int creator, int reported, String reason, int typeId, boolean open) {
        this.id = -1;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.typeId = typeId;
        this.open = open;
    }

    public Report(int creator, int reported, String reason, int typeId) {
        this.id = -1;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.typeId = typeId;
        this.open = true;
    }

    public Report(int id, boolean open) {
        this.id = id;
        this.creator = -1;
        this.reported = -1;
        this.reason = null;
        this.typeId = -1;
        this.open = open;
    }
}
