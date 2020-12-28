package dev.dommi.gameserver.backend.adapters.database.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Collection;

public interface UserRepository {

    public void save(String name, String email);

    public Collection<UserEntity> getAll();

    public void update(int userId, String name, String email);

    public UserEntity findById(int userId);

    public void delete(int userId);
}
