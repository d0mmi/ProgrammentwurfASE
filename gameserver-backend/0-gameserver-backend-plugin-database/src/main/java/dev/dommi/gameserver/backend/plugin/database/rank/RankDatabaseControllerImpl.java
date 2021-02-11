package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.adapter.database.rank.RankDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.rank.UserRank;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;

public class RankDatabaseControllerImpl implements RankDatabaseController {

    private RankTableWrapper rankWrapper;
    private UserRankTableWrapper userRankWrapper;

    public RankDatabaseControllerImpl() {
        rankWrapper = new RankTableWrapper(MariaDBConnector.getInstance());
        userRankWrapper = new UserRankTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public Rank getRankFrom(int userId) throws SQLException {
        UserRank userRank = userRankWrapper.findByUserId(userId);
        if (userRank == null) return null;
        return rankWrapper.findById(userRank.rankId);
    }

    @Override
    public int getRankIdFrom(String name) throws SQLException {
        return rankWrapper.findByName(name).id;
    }

    @Override
    public void grantRank(int userId, int rankId) throws SQLException {
        userRankWrapper.create(new UserRank(userId, rankId));
    }

    @Override
    public void revokeRank(int userId, int rankId) throws SQLException {
        userRankWrapper.deleteRankByUserId(userId, rankId);
    }

    @Override
    public void revokeAllRanks(int userId) throws SQLException {
        userRankWrapper.deleteByUserId(userId);
    }

    @Override
    public void create(Rank value) throws SQLException {
        rankWrapper.create(value);
    }

    @Override
    public void update(Rank value) throws SQLException {
        rankWrapper.update(value);
    }

    @Override
    public void delete(int value) throws SQLException {
        rankWrapper.deleteById(value);
    }

    @Override
    public Rank findById(int value) throws SQLException {
        return rankWrapper.findById(value);
    }

    @Override
    public Collection<Rank> findAll() throws SQLException {
        return rankWrapper.findAll();
    }
}
