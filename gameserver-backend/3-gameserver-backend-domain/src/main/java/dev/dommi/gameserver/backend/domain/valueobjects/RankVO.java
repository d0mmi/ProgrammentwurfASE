package dev.dommi.gameserver.backend.domain.valueobjects;

import java.util.Objects;

public final class RankVO {
    private final int id;
    private final String name;
    private final int level;

    public RankVO(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() {
        return id;
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
            return this.id == rank.getId() && this.name.equals(rank.getName()) && this.level == rank.getLevel();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level);
    }
}
