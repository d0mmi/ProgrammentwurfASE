package dev.dommi.gameserver.backend.adapter.api.login;

import dev.dommi.gameserver.backend.adapter.api.user.User;
import dev.dommi.gameserver.backend.application.login.LoginUser;
import dev.dommi.gameserver.backend.application.login.RegisterUser;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.security.InvalidParameterException;

public class LoginService {

    private static final String WRONG_CREDENTIALS_ERROR = "Wrong email or password!";
    private final RegisterUser registerUser;
    private final LoginUser loginUser;

    public LoginService(UserRepository userRepository, RankRepository rankRepository) {
        registerUser = new RegisterUser(userRepository, rankRepository);
        loginUser = new LoginUser(userRepository);
    }

    public boolean register(String name, String email, String pw) {

        return registerUser.registerUser(name, email, pw);

    }

    public User login(String email, String pw) throws InvalidLoginException {
        try {
            UserEntity userEntity = loginUser.loginUser(email, pw);
            return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getRank().getLevel());
        } catch (InvalidParameterException e) {
            throw new InvalidLoginException(WRONG_CREDENTIALS_ERROR);
        }
    }

}
