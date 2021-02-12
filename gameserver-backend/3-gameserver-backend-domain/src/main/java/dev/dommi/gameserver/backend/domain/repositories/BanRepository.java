package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.entities.BanEntity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface BanRepository {

    void create(int userId, int bannedById, String reason, Date until) throws SQLException;

    void update(int id, String reason, Date until, boolean active) throws SQLException;

    BanEntity findById(int id) throws SQLException;

    Collection<BanEntity> findAll() throws SQLException;

    Collection<BanEntity> findAllByActive(boolean active) throws SQLException;

    Collection<BanEntity> findAllByUser(int userId) throws SQLException;

    Collection<BanEntity> findAllByDate(Date date) throws SQLException;

    Collection<BanEntity> findAllByUserAndDate(int userId, Date date) throws SQLException;

}
