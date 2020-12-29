package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;


public class GetUser {

    public UserEntity getUserById(int id) {
        return new UserRepositoryImpl().findById(id);
    }
}
