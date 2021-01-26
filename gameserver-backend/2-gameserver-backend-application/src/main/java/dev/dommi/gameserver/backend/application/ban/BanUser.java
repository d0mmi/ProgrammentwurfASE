package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class BanUser {

    private BanRepository repository;

    public BanUser(BanRepository repository) {
        this.repository = repository;
    }

    private static final Logger logger = Logger.getLogger(BanUser.class.getName());

    public void banUser(int userId, int bannedById, String reason, Date until) {
        try {
            repository.create(userId, bannedById, reason, until);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }
}
