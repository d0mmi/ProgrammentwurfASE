package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

public class GetAllUsers {
    private final UserRepository repository;

    public GetAllUsers(UserRepository repository) {
        this.repository = repository;
    }

    public Collection<UserRankAggregate> getAllUsers() {
        return repository.getAll();
    }

}
