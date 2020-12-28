package dev.dommi.gameserver.backend.adapters.api.user;


import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.Collection;

public class UserService {

    private UserService() {
    }

    private static UserService instance;

    public void save(String name, String email) {

    }

    public Collection<User> getAll() {
        return null;
    }

    public void update(int userId, String name, String email) {

    }

    public User findById(int userId) {
        return null;
    }

    public void delete(int userId) {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
}
