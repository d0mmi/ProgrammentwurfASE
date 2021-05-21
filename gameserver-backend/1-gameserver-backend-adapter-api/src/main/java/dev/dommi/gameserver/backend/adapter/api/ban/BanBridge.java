package dev.dommi.gameserver.backend.adapter.api.ban;

import dev.dommi.gameserver.backend.adapter.api.user.UserBridge;
import dev.dommi.gameserver.backend.application.ban.BanUser;
import dev.dommi.gameserver.backend.application.ban.CheckUserBan;
import dev.dommi.gameserver.backend.application.ban.GetAllBans;
import dev.dommi.gameserver.backend.application.ban.UpdateBan;
import dev.dommi.gameserver.backend.domain.aggregates.BanAggregate;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.domain.services.ban.BanService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BanBridge {
    private final BanUser banUser;
    private final UpdateBan updateBan;
    private final CheckUserBan checkUserBan;
    private final GetAllBans getAllBans;

    public BanBridge(BanRepository banRepository, UserRepository userRepository) {
        banUser = new BanUser(userRepository, banRepository);
        updateBan = new UpdateBan(banRepository);
        checkUserBan = new CheckUserBan(userRepository, banRepository);
        getAllBans = new GetAllBans(banRepository);
    }

    public void banUser(int userId, int bannedById, String reason, Date until) {
        banUser.banUser(userId, bannedById, reason, until);
    }

    public void update(int id, String reason, Date until, boolean active) {
        updateBan.updateBan(id, reason, until, active);
    }

    public boolean isUserBanned(int userId) {
        return checkUserBan.isUserBanned(userId);
    }

    public boolean isUserBanned(String email) {
        return checkUserBan.isUserBanned(email);
    }

    public Ban getOne(int id) {
        return convertToBanFrom(getAllBans.getOne(id));
    }

    public Collection<Ban> getAll() {
        return convertToBanCollectionFrom(getAllBans.getAll());
    }

    public Collection<Ban> getAll(boolean active) {
        return convertToBanCollectionFrom(getAllBans.getAll(active));
    }

    public Collection<Ban> getAll(int userId) {
        return convertToBanCollectionFrom(getAllBans.getAll(userId));
    }

    public Collection<Ban> getAll(Date date) {
        return convertToBanCollectionFrom(getAllBans.getAll(date));
    }

    public Collection<Ban> getAll(int userId, Date date) {
        return convertToBanCollectionFrom(getAllBans.getAll(userId, date));
    }

    static Ban convertToBanFrom(BanAggregate ban) {
        if (ban == null) return null;

        return new Ban(ban.getId(), UserBridge.convertToUserFrom(ban.getUser()), UserBridge.convertToUserFrom(ban.getBannedBy()), ban.getReason(), ban.getUntil(), ban.isActive());
    }

    static Collection<Ban> convertToBanCollectionFrom(Collection<BanAggregate> entities) {
        Collection<Ban> bans = new ArrayList<>();
        for (BanAggregate ban : entities) {
            if (ban != null) {
                bans.add(convertToBanFrom(ban));
            }
        }
        return bans;
    }

}
