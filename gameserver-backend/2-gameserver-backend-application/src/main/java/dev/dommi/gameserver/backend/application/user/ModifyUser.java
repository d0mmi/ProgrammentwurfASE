package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
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

    public boolean modifyUserById(int id, String name, String email, String pw) {
        try {
            UserEntity user = repository.findById(id);
            return user.modify(name, email, pw, repository);

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    public void deleteUserById(int id) {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
