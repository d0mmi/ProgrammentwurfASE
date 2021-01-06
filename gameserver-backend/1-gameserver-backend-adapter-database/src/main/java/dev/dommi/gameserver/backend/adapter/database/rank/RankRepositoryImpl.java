package dev.dommi.gameserver.backend.adapter.database.rank;

import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;
import dev.dommi.gameserver.backend.plugin.database.rank.Rank;
import dev.dommi.gameserver.backend.plugin.database.rank.RankController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class RankRepositoryImpl implements RankRepository {

    private RankController controller;

    public RankRepositoryImpl() {
        this.controller = new RankController();
    }

    @Override
    public Collection<RankVO> getAllRanks() throws SQLException {
        return convertToRankVOCollectionFrom(controller.getAllRanks());
    }

    @Override
    public RankVO getRankFrom(int userId) throws SQLException {
        return convertToRankVOFrom(controller.getRankFrom(userId));
    }

    @Override
    public int getRankIdFrom(String name) throws SQLException {
        return controller.getRankIdFrom(name);
    }

    @Override
    public void grantRank(int userId, int rankId) throws SQLException {
        controller.grantRank(userId, rankId);
    }

    @Override
    public void revokeRank(int userId, int rankId) throws SQLException {
        controller.revokeRank(userId, rankId);
    }

    @Override
    public void revokeAllRanks(int userId) throws SQLException {
        controller.revokeAllRanks(userId);
    }

    private RankVO convertToRankVOFrom(Rank rank) {
        if (rank == null) return null;
        return new RankVO(rank.name, rank.level);
    }

    private Collection<RankVO> convertToRankVOCollectionFrom(Collection<Rank> ranks) {
        Collection<RankVO> valueObjects = new ArrayList<>();
        for (Rank rank : ranks) {
            if (rank != null) {
                valueObjects.add(convertToRankVOFrom(rank));
            }
        }
        return valueObjects;
    }

}