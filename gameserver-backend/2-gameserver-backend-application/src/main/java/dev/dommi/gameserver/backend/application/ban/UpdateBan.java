package dev.dommi.gameserver.backend.application.ban;

import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;

import java.util.Date;

public class UpdateBan {

    private final BanRepository banRepository;

    public UpdateBan(BanRepository banRepository) {
        this.banRepository = banRepository;
    }

    public void updateBan(int id, String reason, Date until, boolean active) {
        BanAggregate banAggregate = banRepository.findById(id);
        if (banAggregate.update(reason, until, active)) {
            banRepository.update(banAggregate);
        }
    }
}
