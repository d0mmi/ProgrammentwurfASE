package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class GetAllUsers {
    private static final Logger logger = Logger.getLogger(GetAllUsers.class.getName());

    public Collection<UserEntity> getAllUsers() {
        try {
            return new UserRepositoryImpl().getAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

}
