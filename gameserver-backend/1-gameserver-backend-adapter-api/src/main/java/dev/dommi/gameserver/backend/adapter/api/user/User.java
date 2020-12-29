package dev.dommi.gameserver.backend.adapter.api.user;

public class User {
    public int id;
    public String name;
    public String email;
    public String pw;
    public int level;

    public User(int id, String name, String email, String pw, int level) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.level = level;
    }

}
