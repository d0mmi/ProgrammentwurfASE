package dev.dommi.gameserver.backend.plugin.database.user;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.domain.services.auth.AuthService;

import java.util.logging.Logger;

public class AuthServiceImpl implements AuthService {

    private final UserDatabaseControllerImpl userDatabaseController;

    public AuthServiceImpl(UserDatabaseControllerImpl userDatabaseController) {
        this.userDatabaseController = userDatabaseController;
    }

    @Override
    public boolean verifyPassword(String email, String password) {

        User user = userDatabaseController.findByEmail(email);
        if (user != null) {
            return userDatabaseController.checkPassword(user.pw, password);
        }
        return false;
    }
}
