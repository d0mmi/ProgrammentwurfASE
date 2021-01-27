package dev.dommi.gameserver.backend.domain.valueobjects;

import java.util.Objects;

public class ReportTypeVO {
    private final String name;

    public ReportTypeVO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReportTypeVO) {
            ReportTypeVO type = (ReportTypeVO) obj;
            return this.name.equals(type.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
