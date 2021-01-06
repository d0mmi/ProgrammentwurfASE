package dev.dommi.gameserver.backend.application.login;

import dev.dommi.gameserver.backend.adapter.database.rank.RankRepositoryImpl;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.application.rank.RankType;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RegisterUser {

    private static final Logger logger = Logger.getLogger(RegisterUser.class.getName());
    private static final String NAME_REGEX = "(?:[A-Z]|[a-z]|[0-9]|_){4,16}";
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PW_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    /*
            ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once
        (?=\S+$)          # no whitespace allowed in the entire string
        .{8,}             # anything, at least eight places though
        $                 # end-of-string
     */

    public boolean registerUser(String name, String email, String pw) {
        try {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();

            if (userRepository.findByEmail(email) == null && name.matches(NAME_REGEX) && email.matches(EMAIL_REGEX) && pw.matches(PW_REGEX)) {
                userRepository.create(name, email, pw);
                RankRepositoryImpl repository = new RankRepositoryImpl();
                repository.grantRank(userRepository.findByEmail(email).id, repository.getRankIdFrom(RankType.USER.value));
                return true;
            }

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

}
