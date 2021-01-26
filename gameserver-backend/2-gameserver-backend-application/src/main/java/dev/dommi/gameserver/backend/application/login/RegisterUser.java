package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.entities.RankType;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RegisterUser {

    private static final Logger logger = Logger.getLogger(RegisterUser.class.getName());


    private UserRepository userRepository;
    private RankRepository rankRepository;

    public RegisterUser(UserRepository userRepository, RankRepository rankRepository) {
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
    }

    public boolean registerUser(String name, String email, String pw) {
        try {
            if (userRepository.findByEmail(email) == null && name.matches(UserEntity.NAME_REGEX) && email.matches(UserEntity.EMAIL_REGEX) && pw.matches(UserEntity.PW_REGEX)) {
                userRepository.create(name, email, pw);
                rankRepository.grantRank(userRepository.findByEmail(email).getId(), rankRepository.getRankIdFrom(RankType.USER.value));
                return true;
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}
