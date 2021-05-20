package dev.dommi.gameserver.backend.adapter.api.rank;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.Collection;

public class UserRepositoryMock implements UserRepository {
    @Override
    public boolean create(String name, String email, String pw) {
        return true;
    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return false;
    }

    @Override
    public Collection<UserRankAggregate> getAll() {
        return null;
    }

    @Override
    public boolean update(UserRankAggregate user) {
        return true;
    }

    @Override
    public boolean changePassword(int id, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public UserRankAggregate findByEmail(String email) {
        return null;
    }

    @Override
    public UserRankAggregate findById(int userId) {
        return null;
    }

    @Override
    public boolean delete(int userId) {
        return true;
    }
}
