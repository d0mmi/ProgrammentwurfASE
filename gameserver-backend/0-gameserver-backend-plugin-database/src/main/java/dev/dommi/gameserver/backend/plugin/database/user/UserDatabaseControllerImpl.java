package dev.dommi.gameserver.backend.plugin.database.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.adapter.database.user.UserDatabaseController;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

public class UserDatabaseControllerImpl implements UserDatabaseController {

    private static final Logger logger = Logger.getLogger(UserDatabaseControllerImpl.class.getName());
    public static final String BCRYPT_COST = "BCRYPT_COST";
    private UserTableWrapper wrapper;

    public UserDatabaseControllerImpl() {
        wrapper = new UserTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {

        String password;
        try {
            password = wrapper.findByEmail(email).pw;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            return false;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(pw.toCharArray(), password);

        return result.verified;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        return wrapper.findByEmail(email);
    }

    @Override
    public User findById(int id) throws SQLException {
        return wrapper.findById(id);
    }

    @Override
    public void delete(int id) throws SQLException {
        wrapper.deleteById(id);
    }

    @Override
    public Collection<User> findAll() throws SQLException {
        return wrapper.findAll();
    }

    @Override
    public void create(User user) throws SQLException {
        user.pw = BCrypt.withDefaults().hashToString(Integer.parseInt(System.getenv(BCRYPT_COST)), user.pw.toCharArray());
        wrapper.create(user);
    }

    @Override
    public void update(User user) throws SQLException {
        wrapper.update(user);
    }


}
