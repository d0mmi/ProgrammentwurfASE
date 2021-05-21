package dev.dommi.gameserver.backend.adapter.api.user;

import dev.dommi.gameserver.backend.application.user.GetAllUsers;
import dev.dommi.gameserver.backend.application.user.GetUser;
import dev.dommi.gameserver.backend.application.user.ModifyUser;
import dev.dommi.gameserver.backend.application.user.UserNotFoundException;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.auth.ChangePasswordService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class UserBridge {

    private static final Logger logger = Logger.getLogger(UserBridge.class.getName());

    private final GetAllUsers getAllUsers;
    private final ModifyUser modifyUser;
    private final GetUser getUser;

    public UserBridge(UserRepository userRepository, ChangePasswordService changePasswordService) {
        getAllUsers = new GetAllUsers(userRepository);
        modifyUser = new ModifyUser(userRepository, changePasswordService);
        getUser = new GetUser(userRepository);
    }

    public Collection<User> getAll() {
        return convertToUserCollectionFromAggregate(getAllUsers.getAllUsers());
    }

    public boolean update(int userId, String name, String email) {
        return modifyUser.modifyUserById(userId, name, email);
    }

    public boolean changePassword(int userId, String oldPw, String newPw) {
        return modifyUser.changePassword(userId, oldPw, newPw);
    }

    public User findById(int userId) {
        try {
            return convertToUserFrom(getUser.getUserById(userId));
        } catch (UserNotFoundException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    public void delete(int userId) {
        modifyUser.deleteUserById(userId);
    }

    public static User convertToUserFrom(UserRankAggregate user) {
        return new User(user.getUserId(), user.getUserName(), user.getEmail(), user.getRankLevel());
    }

    static Collection<User> convertToUserCollectionFromAggregate(Collection<UserRankAggregate> users) {
        Collection<User> entities = new ArrayList<>();
        for (UserRankAggregate user : users) {
            entities.add(convertToUserFrom(user));
        }
        return entities;
    }

    public static User convertToUserFrom(UserEntity user) {
        return new User(user.getId(), user.getName(), user.getEmail(), 0);
    }

    static Collection<User> convertToUserCollectionFrom(Collection<UserEntity> users) {
        Collection<User> entities = new ArrayList<>();
        for (UserEntity user : users) {
            entities.add(convertToUserFrom(user));
        }
        return entities;
    }
}
