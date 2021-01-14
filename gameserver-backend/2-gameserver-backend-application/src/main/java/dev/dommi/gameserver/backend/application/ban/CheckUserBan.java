package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.adapter.database.ban.BanRepository;
import dev.dommi.gameserver.backend.adapter.database.ban.BanRepositoryImpl;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepository;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class CheckUserBan {
    private static final Logger logger = Logger.getLogger(CheckUserBan.class.getName());


    private BanRepository banRepository;
    private UserRepository userRepository;

    public CheckUserBan(BanRepository banRepository, UserRepository userRepository) {
        this.banRepository = banRepository;
        this.userRepository = userRepository;
    }

    public CheckUserBan() {
        this.banRepository = new BanRepositoryImpl();
        this.userRepository = new UserRepositoryImpl();
    }

    public boolean isBanned(int userId) {
        try {
            return !banRepository.findAllByUserAndDate(userId, new Date()).isEmpty();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return true;
    }

    public boolean isBanned(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email);
            if (user != null) {
                return isBanned(user.id);
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return true;
    }

}
