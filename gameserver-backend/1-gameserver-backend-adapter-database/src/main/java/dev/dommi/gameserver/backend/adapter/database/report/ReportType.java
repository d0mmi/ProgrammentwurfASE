package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;

import java.util.ArrayList;
import java.util.List;

public class ReportType extends DatabaseObject {
    public String name;

    public ReportType(int id, String name) {
        super(id);
        this.name = name;
    }

    public ReportType(String name) {
        super(-1);
        this.name = name;
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
        return values;
    }
}
