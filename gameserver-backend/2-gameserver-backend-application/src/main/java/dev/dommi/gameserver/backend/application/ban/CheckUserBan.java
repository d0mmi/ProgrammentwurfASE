package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.adapter.database.ban.BanRepositoryImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class CheckUserBan {
    private static final Logger logger = Logger.getLogger(CheckUserBan.class.getName());

    public boolean isBanned(int userId) {
        try {
            return !new BanRepositoryImpl().findAllByUserAndDate(userId, new Date()).isEmpty();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return true;
    }

}
