package dev.dommi.gameserver.backend.adapter.api.user;

public class User {
    public int id;
    public String name;
    public String email;
    public int level;

    public User(int id, String name, String email, int level) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.level = level;
    }

}
