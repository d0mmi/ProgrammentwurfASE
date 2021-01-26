package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.adapter.database.ban.BanRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

public class GetAllBans {
    private static final Logger logger = Logger.getLogger(GetAllBans.class.getName());

    public GetAllBans(BanRepository repository) {
        this.repository = repository;
    }

    private BanRepository repository;

    public BanEntity getOne(int id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    public Collection<BanEntity> getAll() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(int userId) {
        try {
            return repository.findAllByUser(userId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(boolean active) {
        try {
            return repository.findAllByActive(active);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(Date date) {
        try {
            return repository.findAllByDate(date);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Collection<BanEntity> getAll(int userId, Date date) {
        try {
            return repository.findAllByUserAndDate(userId, date);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }
}
