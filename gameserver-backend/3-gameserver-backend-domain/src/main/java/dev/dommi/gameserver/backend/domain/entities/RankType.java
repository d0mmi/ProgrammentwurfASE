package dev.dommi.gameserver.backend.domain.entities;

public enum RankType {
    USER("User"), MODERATOR("Moderator"), ADMIN("Admin");

    public String value;

    RankType(String value) {
        this.value = value;
    }
}
