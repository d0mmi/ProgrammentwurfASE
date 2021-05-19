package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.util.Collection;

public class RankRepositoryImpl implements RankRepository {

    private final RankDatabaseController controller;

    public RankRepositoryImpl(RankDatabaseController controller) {
        this.controller = controller;
    }

    @Override
    public Collection<RankVO> getAllRanks() {
        return RankMapper.getUserEntityCollectionFrom(controller.findAll());
    }

    @Override
    public RankVO getRankFrom(int userId) {
        return RankMapper.getRankVOFrom(controller.getRankFrom(userId));
    }

    @Override
    public int getRankIdFrom(String name) {
        return controller.getRankIdFrom(name);
    }

    @Override
    public boolean grantRank(int userId, int rankId) {
        return controller.grantRank(userId, rankId);
    }

    @Override
    public boolean revokeRank(int userId, int rankId) {
        return controller.revokeRank(userId, rankId);
    }

    @Override
    public boolean revokeAllRanks(int userId) {
        return controller.revokeAllRanks(userId);
    }

}
