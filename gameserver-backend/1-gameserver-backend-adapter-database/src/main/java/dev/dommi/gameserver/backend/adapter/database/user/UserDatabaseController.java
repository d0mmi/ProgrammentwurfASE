package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;

public interface UserDatabaseController extends DatabaseController<User> {

    boolean verifyPasswordByEmail(String email, String pw);
    User findByEmail(String email) throws SQLException;
}
