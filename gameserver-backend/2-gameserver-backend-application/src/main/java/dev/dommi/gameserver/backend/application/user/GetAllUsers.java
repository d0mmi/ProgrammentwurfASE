package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepository;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class GetAllUsers {
    private static final Logger logger = Logger.getLogger(GetAllUsers.class.getName());
    private UserRepository repository;

    public GetAllUsers(UserRepository repository) {
        this.repository = repository;
    }

    public GetAllUsers() {
        repository = new UserRepositoryImpl();
    }

    public Collection<UserEntity> getAllUsers() {
        try {
            return repository.getAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

}
