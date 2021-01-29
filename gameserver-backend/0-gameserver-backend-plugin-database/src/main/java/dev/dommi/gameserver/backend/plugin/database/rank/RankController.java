package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.Collection;

public class RankController {

    private RankTableWrapper rankWrapper;
    private UserRankTableWrapper userRankWrapper;

    public RankController() {
        rankWrapper = new RankTableWrapper(MariaDBConnector.getInstance());
        userRankWrapper = new UserRankTableWrapper(MariaDBConnector.getInstance());
    }

    public Collection<Rank> getAllRanks() throws SQLException {
        return rankWrapper.findAll();
    }

    public Rank getRankFrom(int userId) throws SQLException {
        UserRank userRank = userRankWrapper.findByUserId(userId);
        if(userRank == null) return null;
        return rankWrapper.findById(userRank.rankId);
    }

    public int getRankIdFrom(String name) throws SQLException {
        return rankWrapper.findByName(name).id;
    }

    public void grantRank(int userId, int rankId) throws SQLException {
        userRankWrapper.create(new UserRank(userId, rankId));
    }

    public void revokeRank(int userId, int rankId) throws SQLException {
        userRankWrapper.deleteRankByUserId(userId, rankId);
    }

    public void revokeAllRanks(int userId) throws SQLException {
        userRankWrapper.deleteByUserId(userId);
    }

}
