package dev.dommi.gameserver.backend.adapter.api.ban;

import dev.dommi.gameserver.backend.application.ban.BanUser;
import dev.dommi.gameserver.backend.application.ban.CheckUserBan;
import dev.dommi.gameserver.backend.application.ban.GetAllBans;
import dev.dommi.gameserver.backend.application.ban.UpdateBan;
import dev.dommi.gameserver.backend.domain.entities.BanEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BanService {

    private BanService() {

    }

    public static void banUser(int userId, int bannedById, String reason, Date until) {
        new BanUser().banUser(userId, bannedById, reason, until);
    }

    public static void update(int id, String reason, Date until, boolean active) {
        new UpdateBan().updateBan(id, reason, until, active);
    }

    //TODO Implement check in other Requests
    public static boolean isUserBaned(int userId) {
        return new CheckUserBan().isBanned(userId);
    }

    public static boolean isUserBaned(String email) {
        return new CheckUserBan().isBanned(email);
    }

    public static Ban getOne(int id) {
        return convertToBanFrom(new GetAllBans().getOne(id));
    }

    public static Collection<Ban> getAll() {
        return convertToBanCollectionFrom(new GetAllBans().getAll());
    }

    public static Collection<Ban> getAll(boolean active) {
        return convertToBanCollectionFrom(new GetAllBans().getAll(active));
    }

    public static Collection<Ban> getAll(int userId) {
        return convertToBanCollectionFrom(new GetAllBans().getAll(userId));
    }

    public static Collection<Ban> getAll(Date date) {
        return convertToBanCollectionFrom(new GetAllBans().getAll(date));
    }

    public static Collection<Ban> getAll(int userId, Date date) {
        return convertToBanCollectionFrom(new GetAllBans().getAll(userId, date));
    }

    static Ban convertToBanFrom(BanEntity ban) {
        if (ban == null) return null;
        return new Ban(ban.id, ban.userId, ban.bannedById, ban.reason, ban.until, ban.active);
    }

    static Collection<Ban> convertToBanCollectionFrom(Collection<BanEntity> entities) {
        Collection<Ban> bans = new ArrayList<>();
        for (BanEntity ban : entities) {
            if (ban != null) {
                bans.add(convertToBanFrom(ban));
            }
        }
        return bans;
    }

}
