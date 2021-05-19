package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.adapter.database.rank.RankDatabaseController;
import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {

    private final UserDatabaseController controller;
    private final RankDatabaseController rankController;

    public UserRepositoryImpl(UserDatabaseController controller, RankDatabaseController rankController) {
        this.controller = controller;
        this.rankController = rankController;
    }

    @Override
    public boolean create(String name, String email, String pw) {
        return controller.create(new User(name, email, pw));
    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return controller.verifyPasswordByEmail(email, pw);
    }

    @Override
    public Collection<UserRankAggregate> getAll() {
        Collection<User> users = controller.findAll();
        return convertCollection(users);
    }

    @Override
    public boolean update(int userId, String name, String email, String pw) {
        return controller.update(new User(userId, name, email, pw));
    }

    @Override
    public UserRankAggregate findByEmail(String email) {
        User user = controller.findByEmail(email);
        Rank rank = rankController.getRankFrom(user.id);
        return UserMapper.getUserRankAggregateFrom(user, rank);
    }

    @Override
    public UserRankAggregate findById(int userId) {
        User user = controller.findById(userId);
        Rank rank = rankController.getRankFrom(user.id);
        return UserMapper.getUserRankAggregateFrom(user, rank);
    }

    @Override
    public boolean delete(int userId) {
        return controller.delete(userId);
    }


    Collection<UserRankAggregate> convertCollection(Collection<User> users) {
        Collection<UserRankAggregate> entities = new ArrayList<>();
        for (User user : users) {
            if (user != null) {
                Rank rank = rankController.getRankFrom(user.id);
                entities.add(UserMapper.getUserRankAggregateFrom(user, rank));
            }
        }
        return entities;
    }

}
