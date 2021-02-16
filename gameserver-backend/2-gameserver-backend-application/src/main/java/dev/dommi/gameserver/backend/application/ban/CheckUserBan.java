package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;

import java.sql.SQLException;
import java.util.logging.Logger;

public class CheckUserBan {
    private static final Logger logger = Logger.getLogger(CheckUserBan.class.getName());


    private BanRepository banRepository;
    private UserRepository userRepository;

    public CheckUserBan(BanRepository banRepository, UserRepository userRepository) {
        this.banRepository = banRepository;
        this.userRepository = userRepository;
    }

    private boolean isBanned(UserEntity user) {
        try {
            return user.isBanned(banRepository);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return true;
    }

    public boolean isBanned(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email);
            if (user != null) {
                return isBanned(user);
            }
            return false;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return true;
    }

    public boolean isBanned(int userId) {
        try {
            UserEntity user = userRepository.findById(userId);
            if (user != null) {
                return isBanned(user);
            }
            return false;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return true;
    }

}
