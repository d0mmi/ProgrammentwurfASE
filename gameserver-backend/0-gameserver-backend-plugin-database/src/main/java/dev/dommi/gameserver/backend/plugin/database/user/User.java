package dev.dommi.gameserver.backend.plugin.database.user;


public final class User {
    public int id;
    public String name;
    public String email;
    public String pw;

    public User(int id, String name, String email, String pw) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public User(String name, String email, String pw) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.pw = pw;
    }
}
