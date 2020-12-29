package dev.dommi.gameserver.backend.plugin.api.services.login;

public class RegisterRequest {
    public String name;
    public String email;
    public String pw;

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String email, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
    }
}

