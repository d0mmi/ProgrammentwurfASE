package dev.dommi.gameserver.backend.adapter.api.login;

import com.sun.javaws.exceptions.InvalidArgumentException;
import dev.dommi.gameserver.backend.adapter.api.user.User;
import dev.dommi.gameserver.backend.application.login.LoginUser;
import dev.dommi.gameserver.backend.application.login.RegisterUser;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

public class LoginService {

    private static final String WRONG_CREDENTIALS_ERROR = "Wrong email or password!";

    public static boolean register(String name, String email, String pw) {

        return new RegisterUser().registerUser(name, email, pw);

    }

    public static User login(String email, String pw) throws InvalidLoginException {
        try {
            UserEntity userEntity = new LoginUser().loginUser(email, pw);
            return new User(userEntity.id, userEntity.name, userEntity.email, userEntity.pw, 1); //TODO get level from Rank
        } catch (InvalidArgumentException e) {
            throw new InvalidLoginException(WRONG_CREDENTIALS_ERROR);
        }
    }

}
