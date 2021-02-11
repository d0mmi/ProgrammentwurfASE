package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;

public interface UserDatabaseController extends DatabaseController<User> {

    public boolean verifyPasswordByEmail(String email, String pw);
    public User findByEmail(String email) throws SQLException;
}
