package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.ban.BanService;
import dev.dommi.gameserver.backend.domain.services.ban.CheckBanService;

import java.util.Date;


public class CheckUserBan implements CheckBanService {

    private final UserRepository userRepository;
    private final BanRepository banRepository;

    public CheckUserBan(UserRepository userRepository, BanRepository banRepository) {
        this.userRepository = userRepository;
        this.banRepository = banRepository;
    }


    public boolean isUserBanned(String email) {
        return isUserBanned(userRepository.findByEmail(email));
    }

    public boolean isUserBanned(int userId) {
        return isUserBanned(userRepository.findById(userId));
    }

    @Override
    public boolean isUserBanned(UserRankAggregate user) {
        if (user == null) return true;
        return !banRepository.findAllByUserAndDate(user.getUserId(), new Date()).isEmpty();
    }
}
