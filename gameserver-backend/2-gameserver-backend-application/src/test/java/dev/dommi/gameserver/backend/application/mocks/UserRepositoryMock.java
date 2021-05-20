package dev.dommi.gameserver.backend.application.mocks;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
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
    public boolean create(String name, String email, String pw) {
        return true;
    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return true;
    }

    @Override
    public Collection<UserRankAggregate> getAll() {
        return Arrays.asList(findById(1));
    }

    @Override
    public boolean update(UserRankAggregate user) {
        return true;
    }

    @Override
    public boolean changePassword(int id, String oldPassword, String newPassword) {
        return true;
    }

    @Override
    public UserRankAggregate findByEmail(String email) {
        if (returnNullFirstTime) {
            returnNullFirstTime = false;
            return null;
        }
        UserEntity entity = new UserEntity(1, "TestUser", email);
        RankVO rank = new RankVO(2, "Rank", 50);
        return new UserRankAggregate(entity, rank);
    }

    @Override
    public UserRankAggregate findById(int userId) {
        UserEntity entity = new UserEntity(userId, "TestUser", "test@example.com");
        RankVO rank = new RankVO(2, "Rank", 50);
        return new UserRankAggregate(entity, rank);
    }

    @Override
    public boolean delete(int userId) {
        return true;
    }
}
