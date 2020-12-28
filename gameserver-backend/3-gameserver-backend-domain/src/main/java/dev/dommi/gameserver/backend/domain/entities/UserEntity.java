package dev.dommi.gameserver.backend.domain.entities;

public class UserEntity {
    public int id;
    public String name;
    public String email;

    public UserEntity(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
