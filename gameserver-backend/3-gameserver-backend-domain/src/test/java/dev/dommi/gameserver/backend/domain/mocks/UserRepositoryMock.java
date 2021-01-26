package dev.dommi.gameserver.backend.domain.mocks;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class UserRepositoryMock implements UserRepository {

    @Override
    public void create(String name, String email, String pw) throws SQLException {

    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return false;
    }

    @Override
    public Collection<UserEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void update(int userId, String name, String email, String pw) throws SQLException {

    }

    @Override
    public UserEntity findByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public UserEntity findById(int userId) throws SQLException {
        return null;
    }

    @Override
    public void delete(int userId) throws SQLException {

    }
}
