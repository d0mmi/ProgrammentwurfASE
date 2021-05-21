package dev.dommi.gameserver.backend.domain.valueobjects;

import java.util.Objects;

public final class RankVO {
    private final int id;
    private final String name;
    private final int level;

    public RankVO(int id, String name, int level) {
        if (id < 0 && id != -1) throw new IllegalArgumentException("id must be >= 0 or -1 to count as not set");
        this.id = id;
        if (name.length() < 1) throw new IllegalArgumentException("name length must be >= 1");
        this.name = name;
        if (level < 0) throw new IllegalArgumentException("level must be >= 0");
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
