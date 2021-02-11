package dev.dommi.gameserver.backend.adapter.database.ban;

import dev.dommi.gameserver.backend.adapter.database.DatabaseController;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface BanDatabaseController extends DatabaseController<Ban> {

    public Collection<Ban> findAllByActive(boolean active) throws SQLException;
    public Collection<Ban> findAllByUser(int userId) throws SQLException;
    public Collection<Ban> findAllByDate(Date date) throws SQLException;
    public Collection<Ban> findAllByUserAndDate(int userId, Date date) throws SQLException;

}
