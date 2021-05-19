package dev.dommi.gameserver.backend.domain.aggregates;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

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

    public int getUserId() {
        return user.getId();
    }

    public int getBannedById() {
        return bannedBy.getId();
    }

    public String getUserEmail() {
        return user.getEmail();
    }

    public String getBannedByEmail() {
        return bannedBy.getEmail();
    }

    public String getUserName() {
        return user.getName();
    }

    public String getBannedByName() {
        return bannedBy.getName();
    }

    public boolean update(String reason, Date until, boolean active, BanRepository repository) {
        return banEntity.update(reason, until, active, repository);
    }


}
