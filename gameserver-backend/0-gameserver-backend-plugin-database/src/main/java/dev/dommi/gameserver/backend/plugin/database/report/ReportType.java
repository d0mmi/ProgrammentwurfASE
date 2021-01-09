package dev.dommi.gameserver.backend.plugin.database.report;

public class ReportType {
    public int id;
    public String name;

    public ReportType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ReportType(String name) {
        this.id = -1;
        this.name = name;
    }
}
