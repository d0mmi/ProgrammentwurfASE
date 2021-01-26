package dev.dommi.gameserver.backend.domain.valueobjects;

import java.util.Objects;

public final class RankVO {
    private final String name;
    private final int level;

    public RankVO(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RankVO) {
            RankVO rank = (RankVO) obj;
            return this.name.equals(rank.getName()) && this.level == rank.getLevel();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
