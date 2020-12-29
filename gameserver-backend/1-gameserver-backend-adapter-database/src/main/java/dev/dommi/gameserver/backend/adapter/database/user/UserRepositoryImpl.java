package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean create(String name, String email, String pw) {
        return false;
    }

    @Override
    public String getPasswordByEmail(String email) {
        return null;
    }

    @Override
    public Collection<UserEntity> getAll() {
        return null;
    }

    @Override
    public void update(int userId, String name, String email) {

    }

    @Override
    public UserEntity findByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity findById(int userId) {
        return null;
    }

    @Override
    public void delete(int userId) {

    }
}
