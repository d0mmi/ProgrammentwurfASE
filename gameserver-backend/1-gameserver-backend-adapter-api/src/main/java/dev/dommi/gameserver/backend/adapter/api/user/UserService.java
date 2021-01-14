package dev.dommi.gameserver.backend.adapter.api.user;

import dev.dommi.gameserver.backend.application.user.GetAllUsers;
import dev.dommi.gameserver.backend.application.user.GetUser;
import dev.dommi.gameserver.backend.application.user.ModifyUser;
import dev.dommi.gameserver.backend.application.user.UserNotFoundException;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private UserService() {
    }

    public static Collection<User> getAll() {
        return convertToUserCollectionFrom(new GetAllUsers().getAllUsers());
    }

    public static boolean update(int userId, String name, String email) {
        return new ModifyUser().modifyUserById(userId, name, email, null);
    }

    public static User findById(int userId) {
        try {
            return convertToUserFrom(new GetUser().getUserById(userId));
        } catch (UserNotFoundException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    public static void delete(int userId) {
        new ModifyUser().deleteUserById(userId);
    }

    static User convertToUserFrom(UserEntity user) {
        return new User(user.id, user.name, user.email, user.rank.level);
    }

    static Collection<User> convertToUserCollectionFrom(Collection<UserEntity> users) {
        Collection<User> entities = new ArrayList<>();
        for (UserEntity user : users) {
            entities.add(convertToUserFrom(user));
        }
        return entities;
    }
}
