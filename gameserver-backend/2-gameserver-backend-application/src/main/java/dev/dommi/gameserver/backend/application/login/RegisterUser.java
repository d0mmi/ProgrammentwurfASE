package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RegisterUser {

    private static final Logger logger = Logger.getLogger(RegisterUser.class.getName());

    public boolean registerUser(String name, String email, String pw) {
        //TODO Validate Name, email, pw
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();

            if (userRepository.findByEmail(email) == null) {
                new UserRepositoryImpl().create(name, email, pw);
                return true;
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}
