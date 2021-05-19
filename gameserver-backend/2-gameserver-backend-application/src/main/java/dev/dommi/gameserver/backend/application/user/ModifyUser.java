package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;


public class ModifyUser {
    private final UserRepository repository;

    public ModifyUser(UserRepository repository) {
        this.repository = repository;
    }

    public boolean modifyUserById(int id, String name, String email, String pw) {
        UserRankAggregate user = repository.findById(id);
        if (user != null) {
            return user.modifyUser(name, email, pw, repository);
        }
        return false;
    }

    public void deleteUserById(int id) {
        repository.delete(id);
    }
}
