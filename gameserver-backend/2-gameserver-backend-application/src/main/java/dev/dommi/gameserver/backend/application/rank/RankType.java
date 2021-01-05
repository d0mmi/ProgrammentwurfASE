package dev.dommi.gameserver.backend.application.rank;

public enum RankType {
    USER("User"), MODERATOR("Moderator"), ADMIN("Admin");

    public String value;

    RankType(String value) {
        this.value = value;
    }
}
