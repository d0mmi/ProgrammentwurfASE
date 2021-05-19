package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.services.BanService;

import java.util.Date;
import java.util.logging.Logger;

public class BanUser {

    private final BanService banService;

    public BanUser(BanService banService) {
        this.banService = banService;
    }

    private static final Logger logger = Logger.getLogger(BanUser.class.getName());

    public void banUser(int userId, int bannedById, String reason, Date until) {
        banService.banUser(userId, bannedById, reason, until);
    }
}
