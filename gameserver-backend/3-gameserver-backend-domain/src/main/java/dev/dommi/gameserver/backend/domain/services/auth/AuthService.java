package dev.dommi.gameserver.backend.domain.services.auth;

public interface AuthService {

    boolean verifyPassword(String email, String password);

}
