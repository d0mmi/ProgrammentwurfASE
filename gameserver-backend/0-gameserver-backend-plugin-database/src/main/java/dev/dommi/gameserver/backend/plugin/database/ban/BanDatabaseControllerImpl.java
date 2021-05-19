package dev.dommi.gameserver.backend.plugin.database.ban;

import dev.dommi.gameserver.backend.adapter.database.ban.Ban;
import dev.dommi.gameserver.backend.adapter.database.ban.BanDatabaseController;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

public class BanDatabaseControllerImpl implements BanDatabaseController {

    private static final Logger logger = Logger.getLogger(BanDatabaseControllerImpl.class.getName());
    private final BanTableWrapper wrapper;

    public BanDatabaseControllerImpl() {
        wrapper = new BanTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public boolean create(Ban value) {
        try {
            wrapper.create(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Ban value) {
        try {
            wrapper.update(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Ban findById(int id) {
        try {
            return wrapper.findById(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Ban> findAll() {
        try {
            return wrapper.findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Ban> findAllByActive(boolean active) {
        try {
            return wrapper.findAllByActive(active);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Ban> findAllByUser(int userId) {
        try {
            return wrapper.findAllByUser(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Ban> findAllByDate(Date date) {
        try {
            return wrapper.findAllByDate(date);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<Ban> findAllByUserAndDate(int userId, Date date) {
        try {
            return wrapper.findAllByUserAndDate(userId, date);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(int value) {
        try {
            wrapper.deleteById(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }
}
