package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;

import java.util.ArrayList;
import java.util.List;

public class UserRank extends DatabaseObject {
    public int userId;
    public int rankId;

    public UserRank(int id, int userId, int rankId) {
        super(id);
        this.userId = userId;
        this.rankId = rankId;
    }

    public UserRank(int userId, int rankId) {
        super(-1);
        this.userId = userId;
        this.rankId = rankId;
    }

    @Override
    public List<String> getParamNames() {
        List<String> params = new ArrayList<>();
        if (id >= 0) {
            params.add("id");
        } else {
            return new ArrayList<>();
        }
        if (userId >= 0) {
            params.add("userId");
        }
        if (rankId >= 0) {
            params.add("rankId");
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

        if (userId >= 0) {
            values.add(userId);
        }
        if (rankId >= 0) {
            values.add(rankId);
        }
        return values;
    }
}
