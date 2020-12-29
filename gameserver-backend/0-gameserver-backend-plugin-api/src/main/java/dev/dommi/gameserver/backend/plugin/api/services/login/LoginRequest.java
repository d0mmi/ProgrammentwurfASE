package dev.dommi.gameserver.backend.plugin.api.services.login;

public class LoginRequest {
    public String email;
    public String pw;

    public LoginRequest() {
    }

    public LoginRequest(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }
}
