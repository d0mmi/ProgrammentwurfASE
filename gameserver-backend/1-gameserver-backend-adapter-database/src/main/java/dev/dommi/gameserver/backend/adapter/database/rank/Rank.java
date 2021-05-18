package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;

import java.util.ArrayList;
import java.util.List;

public class Rank extends DatabaseObject {
    public String name;
    public int level;

    public Rank(int id, String name, int level) {
        super(id);
        this.name = name;
        this.level = level;
    }

    public Rank(String name, int level) {
        super(-1);
        this.name = name;
        this.level = level;
    }

    @Override
    public List<String> getParamNames() {
        List<String> params = new ArrayList<>();

        if (id >= 0) {
            params.add("id");
        } else {
            return new ArrayList<>();
        }

        if (name != null && !name.isEmpty()) {
            params.add("name");
        }
        if (level >= 0) {
            params.add("level");
        }
        return params;
    }

    @Override
    public List<Object> getValues() {
        List<Object> values = new ArrayList<>();

        if (id >= 0) {
            values.add(id);
        } else {
            return new ArrayList<>();
        }

        if (name != null && !name.isEmpty()) {
            values.add(name);
        }
        if (level >= 0) {
            values.add(level);
        }
        return values;
    }
}
