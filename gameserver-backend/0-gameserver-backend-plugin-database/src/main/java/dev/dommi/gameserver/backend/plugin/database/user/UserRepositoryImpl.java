package dev.dommi.gameserver.backend.plugin.database.user;

import dev.dommi.gameserver.backend.adapters.database.user.UserRepository;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void save(String name, String email) {

    }

    @Override
    public Collection<UserEntity> getAll() {
        return null;
    }

    @Override
    public void update(int userId, String name, String email) {

    }

    @Override
    public UserEntity findById(int userId) {
        return null;
    }

    @Override
    public void delete(int userId) {

    }
}
