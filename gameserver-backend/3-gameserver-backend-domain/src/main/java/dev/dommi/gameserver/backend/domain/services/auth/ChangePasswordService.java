package dev.dommi.gameserver.backend.domain.services.auth;

public interface ChangePasswordService {
    boolean changePassword(int userId, String oldPassword, String newPassword);
}
