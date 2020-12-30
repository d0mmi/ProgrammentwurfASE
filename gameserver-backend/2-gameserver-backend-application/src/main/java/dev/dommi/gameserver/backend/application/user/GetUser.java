package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.logging.Logger;


public class GetUser {

    private static final Logger logger = Logger.getLogger(GetUser.class.getName());

    public UserEntity getUserById(int id) throws UserNotFoundException {

        try {
            UserEntity userEntity = new UserRepositoryImpl().findById(id);
            if (userEntity != null) {
                return userEntity;
            }
            throw new UserNotFoundException("Could not find User with the id: " + id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            throw new UserNotFoundException("Error in database");
        }
    }
}
