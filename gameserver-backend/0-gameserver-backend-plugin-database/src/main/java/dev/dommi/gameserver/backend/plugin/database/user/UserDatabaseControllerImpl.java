package dev.dommi.gameserver.backend.plugin.database.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.adapter.database.user.UserDatabaseController;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class UserDatabaseControllerImpl implements UserDatabaseController {

    private static final Logger logger = Logger.getLogger(UserDatabaseControllerImpl.class.getName());
    public static final String BCRYPT_COST = "BCRYPT_COST";
    private final UserTableWrapper wrapper;

    public UserDatabaseControllerImpl() {
        wrapper = new UserTableWrapper(MariaDBConnector.getInstance());
    }

    public boolean checkPassword(String encryptedPassword, String password) {
        return BCrypt.verifyer().verify(password.toCharArray(), encryptedPassword).verified;
    }

    @Override
    public User findByEmail(String email) {
        try {
            return wrapper.findByEmail(email);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try {
            return wrapper.findById(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            wrapper.deleteById(id);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Collection<User> findAll() {
        try {
            return wrapper.findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean create(User user) {
        try {
            user.pw = encryptPassword(user.pw);
            wrapper.create(user);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    public String encryptPassword(String password) {
        return BCrypt.withDefaults().hashToString(Integer.parseInt(System.getenv(BCRYPT_COST)), password.toCharArray());
    }

    @Override
    public boolean update(User user) {
        try {
            wrapper.update(user);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}
