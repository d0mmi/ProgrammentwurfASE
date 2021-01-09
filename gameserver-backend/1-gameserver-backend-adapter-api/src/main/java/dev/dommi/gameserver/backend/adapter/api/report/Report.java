package dev.dommi.gameserver.backend.adapter.api.report;

public class Report {
    public int id;
    public int creator;
    public int reported;
    public String reason;
    public ReportType type;
    public boolean open;

    public Report(int id, int creator, int reported, String reason, ReportType type, boolean open) {
        this.id = id;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.type = type;
        this.open = open;
    }
}
