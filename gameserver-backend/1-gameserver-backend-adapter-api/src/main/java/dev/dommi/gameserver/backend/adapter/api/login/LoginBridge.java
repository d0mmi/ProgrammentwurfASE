package dev.dommi.gameserver.backend.adapter.api.login;

import dev.dommi.gameserver.backend.adapter.api.user.User;
import dev.dommi.gameserver.backend.adapter.api.user.UserBridge;
import dev.dommi.gameserver.backend.application.login.InvalidCredentialsException;
import dev.dommi.gameserver.backend.application.login.LoginUser;
import dev.dommi.gameserver.backend.application.login.RegisterUser;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.security.InvalidParameterException;

public class LoginBridge {

    private final RegisterUser registerUser;
    private final LoginUser loginUser;

    public LoginBridge(UserRepository userRepository, RankRepository rankRepository) {
        registerUser = new RegisterUser(userRepository, rankRepository);
        loginUser = new LoginUser(userRepository);
    }

    public boolean register(String name, String email, String pw) {

        return registerUser.registerUser(name, email, pw);

    }

    public User login(String email, String pw) throws InvalidCredentialsException {
        UserRankAggregate user = loginUser.loginUser(email, pw);
        return UserBridge.convertToUserFrom(user);
    }

}
