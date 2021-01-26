package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.Collection;

public interface UserRepository {

    public void create(String name, String email, String pw) throws SQLException;

    public boolean verifyPasswordByEmail(String email, String pw);

    public Collection<UserEntity> getAll() throws SQLException;

    public void update(int userId, String name, String email, String pw) throws SQLException;

    public UserEntity findByEmail(String email) throws SQLException;

    public UserEntity findById(int userId) throws SQLException;

    public void delete(int userId) throws SQLException;
}
