package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.user.User;
import dev.dommi.gameserver.backend.adapter.database.user.UserDatabaseController;
import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BanRepositoryImpl implements BanRepository {

    private final BanDatabaseController controller;
    private final UserDatabaseController userDatabaseController;

    public BanRepositoryImpl(BanDatabaseController controller, UserDatabaseController userDatabaseController) {
        this.controller = controller;
        this.userDatabaseController = userDatabaseController;
    }

    @Override
    public boolean create(int userId, int bannedById, String reason, Date until) {
        return controller.create(new Ban(userId, bannedById, reason, until));
    }

    @Override
    public boolean update(int id, String reason, Date until, boolean active) {
        return controller.update(new Ban(id, reason, until, active));
    }

    @Override
    public BanAggregate findById(int id) {
        Ban ban = controller.findById(id);
        User user = userDatabaseController.findById(ban.userId);
        User bannedBy = userDatabaseController.findById(ban.bannedById);
        return BanMapper.getBanAggregateFrom(ban, user, bannedBy);
    }

    @Override
    public Collection<BanAggregate> findAll() {
        return convertCollection(controller.findAll());
    }

    @Override
    public Collection<BanAggregate> findAllByActive(boolean active) {
        return convertCollection(controller.findAllByActive(active));
    }

    @Override
    public Collection<BanAggregate> findAllByUser(int userId) {
        return convertCollection(controller.findAllByUser(userId));
    }

    @Override
    public Collection<BanAggregate> findAllByDate(Date date) {
        return convertCollection(controller.findAllByDate(date));
    }

    @Override
    public Collection<BanAggregate> findAllByUserAndDate(int userId, Date date) {
        return convertCollection(controller.findAllByUserAndDate(userId, date));
    }


    Collection<BanAggregate> convertCollection(Collection<Ban> bans) {
        Collection<BanAggregate> entities = new ArrayList<>();
        for (Ban ban : bans) {
            if (ban != null) {
                User user = userDatabaseController.findById(ban.userId);
                User bannedBy = userDatabaseController.findById(ban.bannedById);
                entities.add(BanMapper.getBanAggregateFrom(ban, user, bannedBy));
            }
        }
        return entities;
    }

}
