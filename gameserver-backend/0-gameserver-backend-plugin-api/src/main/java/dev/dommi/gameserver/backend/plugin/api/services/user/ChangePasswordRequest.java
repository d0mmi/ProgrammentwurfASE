package dev.dommi.gameserver.backend.plugin.api.services.user;

public class ChangePasswordRequest {
    public String oldpw;
    public String newpw;

    public ChangePasswordRequest() {
    }

    public ChangePasswordRequest(String oldpw, String newpw) {
        this.oldpw = oldpw;
        this.newpw = newpw;
    }
}
