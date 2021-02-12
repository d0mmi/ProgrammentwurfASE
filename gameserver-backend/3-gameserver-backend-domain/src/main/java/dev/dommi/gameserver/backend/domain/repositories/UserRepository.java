package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.Collection;

public interface UserRepository {

    void create(String name, String email, String pw) throws SQLException;

    boolean verifyPasswordByEmail(String email, String pw);

    Collection<UserEntity> getAll() throws SQLException;

    void update(int userId, String name, String email, String pw) throws SQLException;

    UserEntity findByEmail(String email) throws SQLException;

    UserEntity findById(int userId) throws SQLException;

    void delete(int userId) throws SQLException;
}
