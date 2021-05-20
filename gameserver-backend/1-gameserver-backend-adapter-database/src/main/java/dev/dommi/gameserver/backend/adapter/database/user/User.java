package dev.dommi.gameserver.backend.adapter.database.user;


import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;

import java.util.ArrayList;
import java.util.List;

public final class User extends DatabaseObject {
    public String name;
    public String email;
    public String pw;

    public User(int id, String name, String email, String pw) {
        super(id);
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public User(int id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
        this.pw = null;
    }

    public User(String name, String email, String pw) {
        super(-1);
        this.name = name;
        this.email = email;
        this.pw = pw;
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

        if (email != null && !email.isEmpty()) {
            params.add("email");
        }

        if (pw != null && !pw.isEmpty()) {
            params.add("pw");
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

        if (email != null && !email.isEmpty()) {
            values.add(email);
        }

        if (pw != null && !pw.isEmpty()) {
            values.add(pw);
        }

        return values;
    }
}
