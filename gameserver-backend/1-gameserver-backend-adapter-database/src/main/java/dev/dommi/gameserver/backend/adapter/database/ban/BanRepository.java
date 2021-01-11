package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface BanRepository {

    public void create(int userId, int bannedById, String reason, Date until) throws SQLException;

    public void update(int id, String reason, Date until, boolean active) throws SQLException;

    public BanEntity findById(int id) throws SQLException;

    public Collection<BanEntity> findAll() throws SQLException;

    public Collection<BanEntity> findAllByActive(boolean active) throws SQLException;

    public Collection<BanEntity> findAllByUser(int userId) throws SQLException;

    public Collection<BanEntity> findAllByDate(Date date) throws SQLException;

    public Collection<BanEntity> findAllByUserAndDate(int userId, Date date) throws SQLException;

}
