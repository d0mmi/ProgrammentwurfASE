package dev.dommi.gameserver.backend.adapter.api.login;

import dev.dommi.gameserver.backend.adapter.api.user.User;
import dev.dommi.gameserver.backend.adapter.api.user.UserBridge;
import dev.dommi.gameserver.backend.application.auth.LoginUser;
import dev.dommi.gameserver.backend.application.auth.RegisterUser;
import dev.dommi.gameserver.backend.domain.services.auth.AuthService;
import dev.dommi.gameserver.backend.domain.services.auth.InvalidCredentialsException;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

public class LoginBridge {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final RankRepository rankRepository;

    public LoginBridge(AuthService authService, UserRepository userRepository, RankRepository rankRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
    }

    public boolean register(String name, String email, String pw) {

        return new RegisterUser(userRepository, rankRepository).registerUser(name, email, pw);

    }

    public User login(String email, String pw) throws InvalidCredentialsException {
        UserRankAggregate user = new LoginUser(userRepository, authService).loginUser(email, pw);
        return UserBridge.convertToUserFrom(user);
    }

}
