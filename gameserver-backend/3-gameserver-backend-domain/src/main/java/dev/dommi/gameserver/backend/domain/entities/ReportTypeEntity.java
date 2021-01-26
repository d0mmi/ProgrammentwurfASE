package dev.dommi.gameserver.backend.domain.entities;

public class ReportTypeEntity {
    private int id;
    private String name;

    public ReportTypeEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
