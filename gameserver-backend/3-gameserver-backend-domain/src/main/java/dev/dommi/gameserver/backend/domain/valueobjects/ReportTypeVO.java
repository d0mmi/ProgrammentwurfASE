package dev.dommi.gameserver.backend.domain.valueobjects;

import java.util.Objects;

public class ReportTypeVO {
    private final int id;
    private final String name;

    public ReportTypeVO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReportTypeVO) {
            ReportTypeVO type = (ReportTypeVO) obj;
            return this.id == type.id && this.name.equals(type.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public ReportTypeVO copy() {
        return new ReportTypeVO(id, name);
    }
}
