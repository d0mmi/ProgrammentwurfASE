package dev.dommi.gameserver.backend.plugin.database.user;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.domain.services.auth.ChangePasswordService;

public class ChangePasswordServiceImpl implements ChangePasswordService {

    private final UserDatabaseControllerImpl userDatabaseController;

    public ChangePasswordServiceImpl(UserDatabaseControllerImpl userDatabaseController) {
        this.userDatabaseController = userDatabaseController;
    }

    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = userDatabaseController.findById(userId);
        if (userDatabaseController.checkPassword(user.pw, oldPassword)) {
            userDatabaseController.update(new User(userId, null, null, userDatabaseController.encryptPassword(newPassword)));
            return true;
        }
        return false;
    }
}
