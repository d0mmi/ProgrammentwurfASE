package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.adapter.database.ban.BanRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

public class GetAllBans {
    private static final Logger logger = Logger.getLogger(GetAllBans.class.getName());

    public Collection<BanEntity> getAll() {
        try {
            return new BanRepositoryImpl().findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(int userId) {
        try {
            return new BanRepositoryImpl().findAllByUser(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(boolean active) {
        try {
            return new BanRepositoryImpl().findAllByActive(active);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(Date date) {
        try {
            return new BanRepositoryImpl().findAllByDate(date);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(int userId, Date date) {
        try {
            return new BanRepositoryImpl().findAllByUserAndDate(userId, date);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }
}
