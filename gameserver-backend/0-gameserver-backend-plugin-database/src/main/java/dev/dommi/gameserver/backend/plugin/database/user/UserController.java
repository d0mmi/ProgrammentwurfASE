package dev.dommi.gameserver.backend.plugin.database.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    public static final String BCRYPT_COST = "BCRYPT_COST";
    private UserTableWrapper wrapper;

    public UserController() {
        wrapper = new UserTableWrapper(MariaDBConnector.getInstance().getConnection());
    }

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

    public User findByEmail(String email) throws SQLException {
        return wrapper.findByEmail(email);
    }

    public User findById(int id) throws SQLException {
        return wrapper.findById(id);
    }

    public void deleteById(int id) throws SQLException {
        wrapper.deleteById(id);
    }

    public Collection<User> findAll() throws SQLException {
        return wrapper.findAll();
    }

    public void createNewUser(User user) throws SQLException {
        user.pw = BCrypt.withDefaults().hashToString(Integer.parseInt(System.getenv(BCRYPT_COST)), user.pw.toCharArray());
        wrapper.create(user);
    }

    public void modifyUser(User user) throws SQLException {
        wrapper.update(user);
    }


}
