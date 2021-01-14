package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.adapter.database.ban.BanRepository;
import dev.dommi.gameserver.backend.adapter.database.ban.BanRepositoryImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class UpdateBan {

    private BanRepository repository;

    public UpdateBan(BanRepository repository) {
        this.repository = repository;
    }

    public UpdateBan() {
        this.repository = new BanRepositoryImpl();
    }

    private static final Logger logger = Logger.getLogger(UpdateBan.class.getName());

    public void updateBan(int id, String reason, Date until, boolean active) {
        try {
            new BanRepositoryImpl().update(id, reason, until, active);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
