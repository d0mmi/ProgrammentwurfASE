package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Collection;

public class GetAllUsers {

    public Collection<UserEntity> getAllUsers(){
        return new UserRepositoryImpl().getAll();
    }

}
