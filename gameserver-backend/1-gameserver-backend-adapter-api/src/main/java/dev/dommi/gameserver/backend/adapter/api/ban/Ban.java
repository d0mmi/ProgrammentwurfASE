package dev.dommi.gameserver.backend.adapter.api.ban;

import dev.dommi.gameserver.backend.adapter.api.user.User;

import java.util.Date;

public class Ban {

    public int id;
    public User user;
    public User bannedBy;
    public String reason;
    public Date until;
    public boolean active;

    public Ban(int id, User user, User bannedBy, String reason, Date until, boolean active) {
        this.id = id;
        this.user = user;
        this.bannedBy = bannedBy;
        this.reason = reason;
        this.until = until;
        this.active = active;
    }
}
