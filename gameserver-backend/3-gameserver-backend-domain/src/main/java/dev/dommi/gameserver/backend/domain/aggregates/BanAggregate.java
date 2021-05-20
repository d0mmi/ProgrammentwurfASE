package dev.dommi.gameserver.backend.domain.aggregates;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Date;

public class BanAggregate {

    private final BanEntity banEntity;
    private final UserEntity user;
    private final UserEntity bannedBy;

    public BanAggregate(BanEntity banEntity, UserEntity user, UserEntity bannedBy) {
        this.banEntity = banEntity;
        this.user = user;
        this.bannedBy = bannedBy;
    }

    public int getId() {
        return banEntity.getId();
    }

    public String getReason() {
        return banEntity.getReason();
    }

    public Date getUntil() {
        return banEntity.getUntil();
    }

    public boolean isActive() {
        return banEntity.isActive();
    }

    public UserEntity getUser() {
        return user.copy();
    }

    public UserEntity getBannedBy() {
        return bannedBy.copy();
    }


    public boolean update(String reason, Date until, boolean active) {
        return banEntity.update(reason, until, active);
    }


}
