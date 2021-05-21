package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.CredentialService;
import dev.dommi.gameserver.backend.domain.services.auth.AuthService;
import dev.dommi.gameserver.backend.domain.services.auth.ChangePasswordService;


public class ModifyUser {
    private final UserRepository repository;
    private final ChangePasswordService changePasswordService;

    public ModifyUser(UserRepository repository, ChangePasswordService changePasswordService) {
        this.repository = repository;
        this.changePasswordService = changePasswordService;
    }

    public boolean modifyUserById(int id, String name, String email) {
        UserRankAggregate user = repository.findById(id);
        if (user != null && (email == null || repository.findByEmail(email) == null)) {
            if (user.modifyUser(name, email)) {
                return repository.update(user);
            }
        }
        return false;
    }

    public boolean changePassword(int id, String oldPw, String newPw) {
        return changePasswordService.changePassword(id, oldPw, newPw);
    }

    public void deleteUserById(int id) {
        repository.delete(id);
    }
}
