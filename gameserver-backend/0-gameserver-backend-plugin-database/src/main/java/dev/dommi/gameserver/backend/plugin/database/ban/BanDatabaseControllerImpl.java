package dev.dommi.gameserver.backend.plugin.database.ban;

import dev.dommi.gameserver.backend.adapter.database.ban.Ban;
import dev.dommi.gameserver.backend.adapter.database.ban.BanDatabaseController;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class BanDatabaseControllerImpl implements BanDatabaseController {

    private BanTableWrapper wrapper;

    public BanDatabaseControllerImpl() {
        wrapper = new BanTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public void create(Ban value) throws SQLException {
        wrapper.create(value);
    }

    @Override
    public void update(Ban value) throws SQLException {
        wrapper.update(value);
    }

    @Override
    public Ban findById(int id) throws SQLException {
        return wrapper.findById(id);
    }

    @Override
    public Collection<Ban> findAll() throws SQLException {
        return wrapper.findAll();
    }

    @Override
    public Collection<Ban> findAllByActive(boolean active) throws SQLException {
        return wrapper.findAllByActive(active);
    }

    @Override
    public Collection<Ban> findAllByUser(int userId) throws SQLException {
        return wrapper.findAllByUser(userId);
    }

    @Override
    public Collection<Ban> findAllByDate(Date date) throws SQLException {
        return wrapper.findAllByDate(date);
    }

    @Override
    public Collection<Ban> findAllByUserAndDate(int userId, Date date) throws SQLException {
        return wrapper.findAllByUserAndDate(userId, date);
    }

    @Override
    public void delete(int value) throws SQLException {
        wrapper.deleteById(value);
    }
}
