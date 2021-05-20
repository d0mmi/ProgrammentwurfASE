package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;


public interface UserDatabaseController extends DatabaseController<User> {

    boolean verifyPasswordByEmail(String email, String pw);

    boolean changePassword(int id, String oldPassword, String newPassword);

    User findByEmail(String email);
}
