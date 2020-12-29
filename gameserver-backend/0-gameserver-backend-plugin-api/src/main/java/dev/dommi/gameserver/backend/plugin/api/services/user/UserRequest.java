package dev.dommi.gameserver.backend.plugin.api.services.user;

public class UserRequest {
    public String name;
    public String email;

    public UserRequest() {
    }

    public UserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
