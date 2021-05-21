package dev.dommi.gameserver.backend.domain.services.ban;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;

import java.util.Date;

public interface CheckBanService {

    boolean isUserBanned(UserRankAggregate user);

}
