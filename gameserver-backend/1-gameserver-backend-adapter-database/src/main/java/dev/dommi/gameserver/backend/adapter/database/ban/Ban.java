package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ban extends DatabaseObject {
    public int userId;
    public int bannedById;
    public String reason;
    public Date until;
    public boolean active;

    public Ban(int id, int userId, int bannedById, String reason, Date until, boolean active) {
        super(id);
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }

    public Ban(int id, int userId, int bannedById, String reason, Date until) {
        super(id);
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = true;
    }

    public Ban(int userId, int bannedById, String reason, Date until) {
        super(-1);
        this.userId = userId;
        this.bannedById = bannedById;
        this.reason = reason;
        this.until = until;
        this.active = true;
    }

    public Ban(int id, String reason, Date until, boolean active) {
        super(id);
        this.userId = -1;
        this.bannedById = -1;
        this.reason = reason;
        this.until = until;
        this.active = active;
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
        if (until != null) {
            params.add("until");
        }

        params.add("active");


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
        if (until != null) {
            values.add(new SimpleDateFormat("yyyy-MM-dd").format(until));
        }

        values.add(active ? 1 : 0);

        return values;
    }
}
