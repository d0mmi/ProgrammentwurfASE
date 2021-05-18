package dev.dommi.gameserver.backend.adapter.database.report;

import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;

import java.util.ArrayList;
import java.util.List;

public class Report extends DatabaseObject {
    public int creator;
    public int reported;
    public String reason;
    public int typeId;
    public boolean open;

    public Report(int id, int creator, int reported, String reason, int typeId, boolean open) {
        super(id);
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.typeId = typeId;
        this.open = open;
    }

    public Report(int creator, int reported, String reason, int typeId, boolean open) {
        super(-1);
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.typeId = typeId;
        this.open = open;
    }

    public Report(int creator, int reported, String reason, int typeId) {
        super(-1);
        this.creator = creator;
        this.reported = reported;
        this.reason = reason;
        this.typeId = typeId;
        this.open = true;
    }

    public Report(int id, boolean open) {
        super(id);
        this.creator = -1;
        this.reported = -1;
        this.reason = null;
        this.typeId = -1;
        this.open = open;
    }

    @Override
    public List<String> getParamNames() {
        List<String> params = new ArrayList<>();

        if (id >= 0) {
            params.add("id");
        } else {
            return new ArrayList<>();
        }

        if (reason != null && !reason.isEmpty()) {
            params.add("reason");
        }
        if (typeId >= 0) {
            params.add("typeId");
        }

        params.add("open");

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

        if (reason != null && !reason.isEmpty()) {
            values.add(reason);
        }
        if (typeId >= 0) {
            values.add(typeId);
        }

        values.add(open ? 1 : 0);
        return values;
    }
}
