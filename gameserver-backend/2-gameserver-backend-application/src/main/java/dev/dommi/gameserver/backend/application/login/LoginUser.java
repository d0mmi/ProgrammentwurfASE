package dev.dommi.gameserver.backend.application.login;

import com.sun.javaws.exceptions.InvalidArgumentException;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

public class LoginUser {
    private static final String WRONG_CREDENTIALS_ERROR = "Wrong email or password!";

    public UserEntity loginUser(String email, String pw) throws InvalidArgumentException {
        try {
            if (new UserRepositoryImpl().verifyPasswordByEmail(email, pw)) {
                return new UserRepositoryImpl().findByEmail(email);
            }
        } catch (Exception e) {
            throw new InvalidArgumentException(new String[]{WRONG_CREDENTIALS_ERROR});
        }

        throw new InvalidArgumentException(new String[]{WRONG_CREDENTIALS_ERROR});
    }
}
