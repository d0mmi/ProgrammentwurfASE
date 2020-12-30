package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ModifyUser {
    private static final Logger logger = Logger.getLogger(ModifyUser.class.getName());

    public boolean modifyUserById(int id, String name, String email, String pw) {
        //TODO Validate Name, email, pw
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();

            if (userRepository.findByEmail(email) == null) {
                userRepository.update(id, name, email, pw);
                return true;
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    public void deleteUserById(int id) {
        try {
            new UserRepositoryImpl().delete(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
