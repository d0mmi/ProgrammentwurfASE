package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.Collection;

public class UserRepositoryMock implements UserRepository {

    @Override
    public void create(String name, String email, String pw) {

    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return false;
    }

    @Override
    public Collection<UserEntity> getAll() {
        return null;
    }

    @Override
    public void update(int userId, String name, String email, String pw) {

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
