package dev.dommi.gameserver.backend.domain.services;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.Date;

public class BanService {

    private final UserRepository userRepository;
    private final BanRepository banRepository;

    public BanService(UserRepository userRepository, BanRepository banRepository) {
        this.userRepository = userRepository;
        this.banRepository = banRepository;
    }

    public boolean banUser(int userId, int bannedById, String reason, Date until) {
        if (userRepository.findById(userId) != null && userRepository.findById(bannedById) != null && new Date().before(until))
            return banRepository.create(userId, bannedById, reason, until);
        return false;
    }


    public boolean isUserBanned(UserRankAggregate user) {
        if (user == null) return true;
        return !banRepository.findAllByUserAndDate(user.getUserId(), new Date()).isEmpty();
    }

    public boolean isUserBanned(UserEntity user) {
        if (user == null) return true;
        return !banRepository.findAllByUserAndDate(user.getId(), new Date()).isEmpty();
    }

    public boolean isUserBanned(String email) {
        return isUserBanned(userRepository.findByEmail(email));
    }

    public boolean isUserBanned(int userId) {
        return isUserBanned(userRepository.findById(userId));
    }
}
