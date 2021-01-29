package dev.dommi.gameserver.backend.plugin.database.ban;

import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class BanController {

    private BanTableWrapper wrapper;

    public BanController() {
        wrapper = new BanTableWrapper(MariaDBConnector.getInstance());
    }

    public void create(Ban value) throws SQLException {
        wrapper.create(value);
    }

    public void update(Ban value) throws SQLException {
        wrapper.update(value);
    }

    public Ban findById(int id) throws SQLException {
        return wrapper.findById(id);
    }

    public Collection<Ban> findAll() throws SQLException {
        return wrapper.findAll();
    }

    public Collection<Ban> findAllByActive(boolean active) throws SQLException {
        return wrapper.findAllByActive(active);
    }

    public Collection<Ban> findAllByUser(int userId) throws SQLException {
        return wrapper.findAllByUser(userId);
    }

    public Collection<Ban> findAllByDate(Date date) throws SQLException {
        return wrapper.findAllByDate(date);
    }

    public Collection<Ban> findAllByUserAndDate(int userId, Date date) throws SQLException {
        return wrapper.findAllByUserAndDate(userId, date);
    }
}
