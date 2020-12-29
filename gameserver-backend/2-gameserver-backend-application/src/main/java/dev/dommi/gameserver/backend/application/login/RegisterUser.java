package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;

public class RegisterUser {

    public boolean registerUser(String name, String email, String pw) {
        //TODO Validate Name, email, pw
        return new UserRepositoryImpl().create(name,email,pw);
    }

}
