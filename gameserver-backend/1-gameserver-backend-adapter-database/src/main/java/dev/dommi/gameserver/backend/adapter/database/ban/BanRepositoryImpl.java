package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;
import dev.dommi.gameserver.backend.plugin.database.ban.Ban;
import dev.dommi.gameserver.backend.plugin.database.ban.BanController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BanRepositoryImpl implements BanRepository {

    private BanController controller;

    public BanRepositoryImpl() {
        controller = new BanController();
    }

    @Override
    public void create(int userId, int bannedById, String reason, Date until) throws SQLException {
        controller.create(new Ban(userId, bannedById, reason, until));
    }

    @Override
    public void update(int id, String reason, Date until, boolean active) throws SQLException {
        controller.update(new Ban(id, reason, until, active));
    }

    @Override
    public BanEntity findById(int id) throws SQLException {
        return convertToBanEntityFrom(controller.findById(id));
    }

    @Override
    public Collection<BanEntity> findAll() throws SQLException {
        return convertToBanEntityCollectionFrom(controller.findAll());
    }

    @Override
    public Collection<BanEntity> findAllByActive(boolean active) throws SQLException {
        return convertToBanEntityCollectionFrom(controller.findAllByActive(active));
    }

    @Override
    public Collection<BanEntity> findAllByUser(int userId) throws SQLException {
        return convertToBanEntityCollectionFrom(controller.findAllByUser(userId));
    }

    @Override
    public Collection<BanEntity> findAllByDate(Date date) throws SQLException {
        return convertToBanEntityCollectionFrom(controller.findAllByDate(date));
    }

    @Override
    public Collection<BanEntity> findAllByUserAndDate(int userId, Date date) throws SQLException {
        return convertToBanEntityCollectionFrom(controller.findAllByUserAndDate(userId, date));
    }


    private BanEntity convertToBanEntityFrom(Ban ban) {
        if (ban == null) return null;
        return new BanEntity(ban.id, ban.userId, ban.bannedById, ban.reason, ban.until, ban.active);
    }

    private Collection<BanEntity> convertToBanEntityCollectionFrom(Collection<Ban> bans) {
        Collection<BanEntity> entities = new ArrayList<>();
        for (Ban ban : bans) {
            if (ban != null) {
                entities.add(convertToBanEntityFrom(ban));
            }
        }
        return entities;
    }

}
