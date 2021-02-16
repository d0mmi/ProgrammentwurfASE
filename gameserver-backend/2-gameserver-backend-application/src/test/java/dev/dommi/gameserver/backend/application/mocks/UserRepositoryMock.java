package dev.dommi.gameserver.backend.application.mocks;

import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class UserRepositoryMock implements UserRepository {

    private boolean returnNullFirstTime;

    public UserRepositoryMock(boolean returnNullFirstTime) {
        this.returnNullFirstTime = returnNullFirstTime;
    }

    public UserRepositoryMock() {
        returnNullFirstTime = false;
    }

    @Override
    public void create(String name, String email, String pw) throws SQLException {

    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return true;
    }

    @Override
    public Collection<UserEntity> getAll() throws SQLException {
        return Arrays.asList(findById(1));
    }

    @Override
    public void update(int userId, String name, String email, String pw) throws SQLException {

    }

    @Override
    public UserEntity findByEmail(String email) throws SQLException {
        if (returnNullFirstTime) {
            returnNullFirstTime = false;
            return null;
        }
        return new UserEntity(1, "ExampleUser", email, new RankVO(1,"User", 1));
    }

    @Override
    public UserEntity findById(int userId) throws SQLException {
        return new UserEntity(userId, "ExampleUser", "test@example.com", new RankVO(1,"User", 1));
    }

    @Override
    public void delete(int userId) throws SQLException {

    }
}
