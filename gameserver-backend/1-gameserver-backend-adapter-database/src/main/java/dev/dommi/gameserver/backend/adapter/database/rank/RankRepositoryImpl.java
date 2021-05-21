package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.domain.aggregates.UserRankAggregate;
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
    public RankVO getRankFrom(String name) {
        return RankMapper.getRankVOFrom(controller.getRankFrom(name));
    }

    @Override
    public boolean update(UserRankAggregate user) {
        return controller.update(user.getUserId(), user.getRankId());
    }
}
