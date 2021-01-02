package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.plugin.database.user.User;
import dev.dommi.gameserver.backend.plugin.database.user.UserController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {

    private UserController controller;

    public UserRepositoryImpl() {
        controller = new UserController();
    }

    @Override
    public void create(String name, String email, String pw) throws SQLException {
        controller.createNewUser(new User(name, email, pw));
    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return controller.verifyPasswordByEmail(email, pw);
    }

    @Override
    public Collection<UserEntity> getAll() throws SQLException {
        Collection<User> users = controller.findAll();
        return convertToUserEntityCollectionFrom(users);
    }

    @Override
    public void update(int userId, String name, String email, String pw) throws SQLException {
        controller.modifyUser(new User(userId, name, email, pw));
    }

    @Override
    public UserEntity findByEmail(String email) throws SQLException {
        User user = controller.findByEmail(email);
        return convertToUserEntityFrom(user);
    }

    @Override
    public UserEntity findById(int userId) throws SQLException {
        User user = controller.findById(userId);
        return new UserEntity(user.id, user.name, user.email);
    }

    @Override
    public void delete(int userId) throws SQLException {
        controller.deleteById(userId);
    }

    private UserEntity convertToUserEntityFrom(User user) {
        if (user == null) return null;
        return new UserEntity(user.id, user.name, user.email);
    }

    private Collection<UserEntity> convertToUserEntityCollectionFrom(Collection<User> users) {
        Collection<UserEntity> entities = new ArrayList<>();
        for (User user : users) {
            if (user != null) {
                entities.add(convertToUserEntityFrom(user));
            }
        }
        return entities;
    }

}
