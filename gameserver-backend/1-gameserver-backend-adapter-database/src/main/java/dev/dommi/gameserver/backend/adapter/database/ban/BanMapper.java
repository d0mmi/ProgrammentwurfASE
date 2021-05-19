package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.adapter.database.user.UserMapper;
import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;

import java.util.ArrayList;
import java.util.Collection;

public class BanMapper {

    public static BanEntity getBanEntityFrom(Ban ban) {
        return new BanEntity(ban.id, ban.reason, ban.until, ban.active);
    }

    public static Collection<BanEntity> getBanEntityCollectionFrom(Collection<Ban> bans) {
        Collection<BanEntity> entities = new ArrayList<>();
        for (Ban ban : bans) {
            if (ban != null) {
                entities.add(getBanEntityFrom(ban));
            }
        }
        return entities;
    }

    public static BanAggregate getBanAggregateFrom(Ban ban, User user, User bannedBy) {
        if (ban == null) return null;
        return new BanAggregate(getBanEntityFrom(ban), UserMapper.getUserEntityFrom(user), UserMapper.getUserEntityFrom(bannedBy));
    }
}
