package dev.dommi.gameserver.backend.application.user;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;


public class GetUser {

    private final UserRepository repository;

    public GetUser(UserRepository repository) {
        this.repository = repository;
    }

    public UserRankAggregate getUserById(int id) throws UserNotFoundException {

        UserRankAggregate user = repository.findById(id);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException("Could not find User with the id: " + id);
    }
}
