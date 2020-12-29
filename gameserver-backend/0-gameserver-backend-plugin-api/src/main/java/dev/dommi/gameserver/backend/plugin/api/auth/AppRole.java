package dev.dommi.gameserver.backend.plugin.api.auth;

import io.javalin.core.security.Role;

public enum AppRole implements Role {
    ANYONE(0), USER(1), MODERATOR(50), ADMINISTRATOR(100);
    public int level;
    AppRole(int level){
        this.level = level;
    }
}
