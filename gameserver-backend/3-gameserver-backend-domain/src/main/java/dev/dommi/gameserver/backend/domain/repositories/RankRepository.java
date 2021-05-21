package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.Collection;

public interface RankRepository {

    Collection<RankVO> getAllRanks();

    RankVO getRankFrom(int userId);

    RankVO getRankFrom(String name);

    boolean update(UserRankAggregate user);

}
