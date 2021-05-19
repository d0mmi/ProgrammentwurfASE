package dev.dommi.gameserver.backend.application.login;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(){
        super("Wrong email or password!");
    }
}
