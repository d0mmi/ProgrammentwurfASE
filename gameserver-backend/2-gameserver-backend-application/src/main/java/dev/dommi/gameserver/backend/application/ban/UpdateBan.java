package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class UpdateBan {

    private BanRepository repository;

    public UpdateBan(BanRepository repository) {
        this.repository = repository;
    }

    private static final Logger logger = Logger.getLogger(UpdateBan.class.getName());

    public void updateBan(int id, String reason, Date until, boolean active) {
        try {
            repository.findById(id).update(reason, until, active, repository);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
