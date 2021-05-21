package dev.dommi.gameserver.backend.application.auth;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.auth.AuthService;
import dev.dommi.gameserver.backend.domain.services.auth.InvalidCredentialsException;

import java.util.logging.Logger;

public class LoginUser {
    private static final Logger logger = Logger.getLogger(LoginUser.class.getName());

    private final UserRepository userRepository;
    private final AuthService authService;

    public LoginUser(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public UserRankAggregate loginUser(String email, String pw) throws InvalidCredentialsException {
        try {
            if (authService.verifyPassword(email, pw)) {
                return userRepository.findByEmail(email);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        throw new InvalidCredentialsException();
    }
}
