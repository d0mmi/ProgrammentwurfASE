package dev.dommi.gameserver.backend.domain.entities;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.Date;

public class UserEntity {
    public static final String NAME_REGEX = "(?:[A-Z]|[a-z]|[0-9]|_){4,16}";
    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String PW_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
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
    private int id;
    private String name;
    private String email;
    private RankVO rank;

    public UserEntity(int id, String name, String email, RankVO rank) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public RankVO getRank() {
        return rank;
    }

    public boolean isBanned(BanRepository banRepository) throws SQLException {
        return !banRepository.findAllByUserAndDate(id, new Date()).isEmpty();
    }

    public boolean modify(String name, String email, String pw, UserRepository userRepository) throws SQLException {
        if (userRepository.findByEmail(email) == null && valuesValid(name, email, pw)) {
            userRepository.update(id, name, email, pw);
            this.name = name;
            this.email = email;
            return true;
        }
        return false;
    }

    public UserEntity grantRank(int rankId, RankRepository rankRepository) throws SQLException {
        revokeRank(rankRepository);
        rankRepository.grantRank(id, rankId);
        rank = rankRepository.getRankFrom(id);
        return this;
    }

    public UserEntity revokeRank(RankRepository rankRepository) throws SQLException {
        if (rank.getName().equalsIgnoreCase(RankType.USER.value)) return this;
        rankRepository.revokeAllRanks(id);
        int rankId = rankRepository.getRankIdFrom(RankType.USER.value);
        return grantRank(rankId, rankRepository);
    }


    private boolean valuesValid(String name, String email, String pw) {
        return (name == null || name.matches(NAME_REGEX)) && (email == null || email.matches(EMAIL_REGEX)) && (pw == null || pw.matches(PW_REGEX));
    }

}
