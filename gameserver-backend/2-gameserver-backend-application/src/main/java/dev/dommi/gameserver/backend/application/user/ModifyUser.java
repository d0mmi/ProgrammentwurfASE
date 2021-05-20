package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.CredentialService;


public class ModifyUser {
    private final UserRepository repository;

    public ModifyUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean modifyUserById(int id, String name, String email) {
        UserRankAggregate user = repository.findById(id);
        if (user != null && (email == null || !new CredentialService().isEmailInUse(repository, email))) {
            if (user.modifyUser(name, email)) {
                return repository.update(user);
            }
        }
        return false;
    }
    public boolean changePassword(int id, String oldPw, String newPw) {
       return repository.changePassword(id,oldPw,newPw);
    }

    public void deleteUserById(int id) {
        repository.delete(id);
    }
}
