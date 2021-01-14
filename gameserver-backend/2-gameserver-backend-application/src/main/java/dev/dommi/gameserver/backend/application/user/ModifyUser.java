package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepository;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.application.login.RegisterUser;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ModifyUser {
    private static final Logger logger = Logger.getLogger(ModifyUser.class.getName());
    private UserRepository repository;

    public ModifyUser(UserRepository repository) {
        this.repository = repository;
    }

    public ModifyUser() {
        repository = new UserRepositoryImpl();
    }

    public boolean modifyUserById(int id, String name, String email, String pw) {
        try {
            if (repository.findByEmail(email) == null && modifyValuesValid(name, email, pw)) {
                repository.update(id, name, email, pw);
                return true;
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    private boolean modifyValuesValid(String name, String email, String pw) {
        return (name == null || name.matches(RegisterUser.NAME_REGEX)) && (email == null || email.matches(RegisterUser.EMAIL_REGEX)) && (pw == null || pw.matches(RegisterUser.PW_REGEX));
    }

    public void deleteUserById(int id) {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
