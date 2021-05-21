package dev.dommi.gameserver.backend.domain.services.auth;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(){
        super("Wrong email or password!");
    }
}
