package dev.dommi.gameserver.backend.adapter.api.login;

public class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}
