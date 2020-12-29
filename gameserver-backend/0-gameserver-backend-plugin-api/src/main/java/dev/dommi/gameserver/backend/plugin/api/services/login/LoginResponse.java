package dev.dommi.gameserver.backend.plugin.api.services.login;

public class LoginResponse {
    public String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }
}
