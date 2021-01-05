package dev.dommi.gameserver.backend.domain.valueobjects;

public class RankVO {
    public String name;
    public int level;

    public RankVO(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
