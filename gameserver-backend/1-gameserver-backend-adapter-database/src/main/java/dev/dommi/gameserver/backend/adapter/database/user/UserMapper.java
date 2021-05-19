package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.adapter.database.rank.RankMapper;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

public class UserMapper {

    public static UserEntity getUserEntityFrom(User user) {
        return new UserEntity(user.id, user.name, user.email);
    }

    public static Collection<UserEntity> getUserEntityCollectionFrom(Collection<User> users) {
        Collection<UserEntity> entities = new ArrayList<>();
        for (User user : users) {
            if (user != null) {
                entities.add(getUserEntityFrom(user));
            }
        }
        return entities;
    }

    public static UserRankAggregate getUserRankAggregateFrom(User user, Rank rank) {
        if (user == null) return null;
        UserEntity userEntity = getUserEntityFrom(user);
        if (rank == null) return new UserRankAggregate(userEntity, null);
        return new UserRankAggregate(userEntity, RankMapper.getRankVOFrom(rank));
    }

}
