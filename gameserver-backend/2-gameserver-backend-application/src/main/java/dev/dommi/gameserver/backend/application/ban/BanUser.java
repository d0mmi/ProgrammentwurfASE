package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.ban.BanService;

import java.util.Date;
import java.util.logging.Logger;

public class BanUser implements BanService {

    private final UserRepository userRepository;
    private final BanRepository banRepository;

    public BanUser(UserRepository userRepository, BanRepository banRepository) {
        this.userRepository = userRepository;
        this.banRepository = banRepository;
    }

    private static final Logger logger = Logger.getLogger(BanUser.class.getName());

    public void banUser(int userId, int bannedById, String reason, Date until) {
        UserRankAggregate user = userRepository.findById(userId);
        UserRankAggregate bannedBy = userRepository.findById(bannedById);
        ban(user, bannedBy, reason, until);
    }

    @Override
    public boolean ban(UserRankAggregate user, UserRankAggregate bannedBy, String reason, Date until) {
        if (user.getRankLevel() >= 50)
            return banRepository.create(user.getUserId(), bannedBy.getUserId(), reason, until);
        return false;
    }
}
