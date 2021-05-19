package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.services.BanService;

import java.util.Date;

public class UpdateBan {

    private final BanService banService;
    private final BanRepository banRepository;

    public UpdateBan(BanService banService, BanRepository banRepository) {
        this.banService = banService;
        this.banRepository = banRepository;
    }

    public void updateBan(int id, String reason, Date until, boolean active) {
        banService.getOne(id).update(reason, until, active, banRepository);
    }
}
