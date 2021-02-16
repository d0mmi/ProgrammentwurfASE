package dev.dommi.gameserver.backend.adapter.database.user;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.adapter.database.rank.RankDatabaseController;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {

    private UserDatabaseController controller;
    private RankDatabaseController rankController;

    public UserRepositoryImpl(UserDatabaseController controller, RankDatabaseController rankController) {
        this.controller = controller;
        this.rankController = rankController;
    }

    @Override
    public void create(String name, String email, String pw) throws SQLException {
        controller.create(new User(name, email, pw));
    }

    @Override
    public boolean verifyPasswordByEmail(String email, String pw) {
        return controller.verifyPasswordByEmail(email, pw);
    }

    @Override
    public Collection<UserEntity> getAll() throws SQLException {
        Collection<User> users = controller.findAll();
        return convertToUserEntityCollectionFrom(users);
    }

    @Override
    public void update(int userId, String name, String email, String pw) throws SQLException {
        controller.update(new User(userId, name, email, pw));
    }

    @Override
    public UserEntity findByEmail(String email) throws SQLException {
        User user = controller.findByEmail(email);
        return convertToUserEntityFrom(user);
    }

    @Override
    public UserEntity findById(int userId) throws SQLException {
        User user = controller.findById(userId);
        return convertToUserEntityFrom(user);
    }

    @Override
    public void delete(int userId) throws SQLException {
        controller.delete(userId);
    }

    UserEntity convertToUserEntityFrom(User user) throws SQLException {
        if (user == null) return null;
        Rank rank = rankController.getRankFrom(user.id);
        if (rank == null) return new UserEntity(user.id, user.name, user.email, null);
        return new UserEntity(user.id, user.name, user.email, new RankVO(rank.id, rank.name, rank.level));
    }

    Collection<UserEntity> convertToUserEntityCollectionFrom(Collection<User> users) throws SQLException {
        Collection<UserEntity> entities = new ArrayList<>();
        for (User user : users) {
            if (user != null) {
                entities.add(convertToUserEntityFrom(user));
            }
        }
        return entities;
    }

}
