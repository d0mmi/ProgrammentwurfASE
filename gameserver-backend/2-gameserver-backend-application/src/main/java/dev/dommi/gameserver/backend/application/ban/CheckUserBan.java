package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.services.BanService;


public class CheckUserBan {

    private final BanService banService;

    public CheckUserBan(BanService banService) {
        this.banService = banService;
    }

    private boolean isBanned(UserEntity user) {
        return banService.isUserBanned(user);
    }

    public boolean isBanned(String email) {
        return banService.isUserBanned(email);
    }

    public boolean isBanned(int userId) {
        return banService.isUserBanned(userId);
    }

}
