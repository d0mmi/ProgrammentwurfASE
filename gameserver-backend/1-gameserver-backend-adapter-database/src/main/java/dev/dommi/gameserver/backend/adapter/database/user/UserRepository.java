package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Collection;

public interface UserRepository {

    public boolean create(String name, String email, String pw);

    public String getPasswordByEmail(String email);

    public Collection<UserEntity> getAll();

    public void update(int userId, String name, String email);

    public UserEntity findByEmail(String email);

    public UserEntity findById(int userId);

    public void delete(int userId);
}
