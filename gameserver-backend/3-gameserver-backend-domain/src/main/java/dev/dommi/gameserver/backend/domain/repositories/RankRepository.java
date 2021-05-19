package dev.dommi.gameserver.backend.domain.repositories;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.Collection;

public interface RankRepository {

    Collection<RankVO> getAllRanks();

    RankVO getRankFrom(int userId);

    int getRankIdFrom(String name);

    boolean grantRank(int userId, int rankId);

    boolean revokeRank(int userId, int rankId);

    boolean revokeAllRanks(int userId);

}
