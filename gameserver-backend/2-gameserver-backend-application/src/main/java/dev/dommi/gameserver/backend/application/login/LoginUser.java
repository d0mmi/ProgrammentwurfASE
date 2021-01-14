package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepository;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.security.InvalidParameterException;

public class LoginUser {
    private static final String WRONG_CREDENTIALS_ERROR = "Wrong email or password!";
    private UserRepository repository;

    public LoginUser(UserRepository repository) {
        this.repository = repository;
    }

    public LoginUser() {
        this.repository = new UserRepositoryImpl();
    }

    public UserEntity loginUser(String email, String pw) throws InvalidParameterException {
        try {
            if (repository.verifyPasswordByEmail(email, pw)) {
                return repository.findByEmail(email);
            }
        } catch (Exception e) {
            throw new InvalidParameterException(WRONG_CREDENTIALS_ERROR);
        }

        throw new InvalidParameterException(WRONG_CREDENTIALS_ERROR);
    }
}
