package dev.dommi.gameserver.backend.plugin.api.auth;

public class JWTSecretMissingException extends Exception {
    public JWTSecretMissingException(String message) {
        super(message);
    }
}
