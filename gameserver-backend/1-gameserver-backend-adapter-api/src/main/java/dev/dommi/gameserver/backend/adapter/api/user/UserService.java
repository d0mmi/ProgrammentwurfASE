package dev.dommi.gameserver.backend.adapter.api.user;

import dev.dommi.gameserver.backend.application.user.GetAllUsers;
import dev.dommi.gameserver.backend.application.user.GetUser;
import dev.dommi.gameserver.backend.application.user.ModifyUser;
import dev.dommi.gameserver.backend.application.user.UserNotFoundException;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final GetAllUsers getAllUsers;
    private final ModifyUser modifyUser;
    private final GetUser getUser;

    public UserService(UserRepository userRepository) {
        getAllUsers = new GetAllUsers(userRepository);
        modifyUser = new ModifyUser(userRepository);
        getUser = new GetUser(userRepository);
    }

    public Collection<User> getAll() {
        return convertToUserCollectionFrom(getAllUsers.getAllUsers());
    }

    public boolean update(int userId, String name, String email) {
        return modifyUser.modifyUserById(userId, name, email, null);
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

    public static User convertToUserFrom(UserEntity user) {
        return new User(user.getId(), user.getName(), user.getEmail(), user.getRank().getLevel());
    }

    static Collection<User> convertToUserCollectionFrom(Collection<UserEntity> users) {
        Collection<User> entities = new ArrayList<>();
        for (UserEntity user : users) {
            entities.add(convertToUserFrom(user));
        }
        return entities;
    }
}
