package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.logging.Logger;

public class LoginUser {
    private static final Logger logger = Logger.getLogger(LoginUser.class.getName());
    private final UserRepository repository;

    public LoginUser(UserRepository repository) {
        this.repository = repository;
    }

    public UserRankAggregate loginUser(String email, String pw) throws InvalidCredentialsException {
        try {
            if (repository.verifyPasswordByEmail(email, pw)) {
                return repository.findByEmail(email);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        throw new InvalidCredentialsException();
    }
}
