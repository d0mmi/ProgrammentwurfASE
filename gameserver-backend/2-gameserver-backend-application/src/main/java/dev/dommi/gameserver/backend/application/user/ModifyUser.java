package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;

public class ModifyUser {
    public void getUserById(int id, String name, String email) {
        new UserRepositoryImpl().update(id, name, email);
    }
}
