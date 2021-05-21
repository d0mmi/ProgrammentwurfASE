package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;


public interface UserDatabaseController extends DatabaseController<User> {

    User findByEmail(String email);
}
