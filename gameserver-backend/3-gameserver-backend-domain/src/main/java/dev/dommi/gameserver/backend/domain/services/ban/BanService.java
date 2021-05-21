package dev.dommi.gameserver.backend.domain.services.ban;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.entities.UserEntity;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;

import java.util.Date;

public interface BanService {

    boolean ban(UserRankAggregate user, UserRankAggregate bannedBy, String reason, Date until);

}
