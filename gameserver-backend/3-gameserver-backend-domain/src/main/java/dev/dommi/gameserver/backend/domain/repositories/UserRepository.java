package dev.dommi.gameserver.backend.domain.repositories;


import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;

import java.util.Collection;

public interface UserRepository {

    boolean create(String name, String email, String pw);

    Collection<UserRankAggregate> getAll();

    boolean update(UserRankAggregate user);

    UserRankAggregate findByEmail(String email);

    UserRankAggregate findById(int userId);

    boolean delete(int userId);
}
