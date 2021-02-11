package dev.dommi.gameserver.backend.adapter.api.report;

import dev.dommi.gameserver.backend.adapter.api.user.User;

public class Report {
    public int id;
    public User creator;
    public User reported;
    public String reason;
    public ReportType type;
    public boolean open;

    public Report(int id, User creator, User reported, String reason, ReportType type, boolean open) {
        this.id = id;
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.type = type;
        this.open = open;
    }
}
