package dev.dommi.gameserver.backend.domain.services;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

public interface RankService {

    boolean grantRankTo(UserRankAggregate user, RankVO rank);

    boolean revokeRankFrom(UserRankAggregate user);

}
