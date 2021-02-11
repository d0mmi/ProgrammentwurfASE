package dev.dommi.gameserver.backend.adapter.database.rank;

public class Rank {
    public int id;
    public String name;
    public int level;

    public Rank(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Rank(String name, int level) {
        this.id = -1;
        this.name = name;
        this.level = level;
    }
}
