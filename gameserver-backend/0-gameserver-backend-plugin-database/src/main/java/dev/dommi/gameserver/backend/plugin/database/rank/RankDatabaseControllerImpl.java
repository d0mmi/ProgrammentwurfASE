package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.adapter.database.rank.RankDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.rank.UserRank;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class RankDatabaseControllerImpl implements RankDatabaseController {

    private static final Logger logger = Logger.getLogger(RankDatabaseControllerImpl.class.getName());
    private final RankTableWrapper rankWrapper;
    private final UserRankTableWrapper userRankWrapper;

    public RankDatabaseControllerImpl() {
        rankWrapper = new RankTableWrapper(MariaDBConnector.getInstance());
        userRankWrapper = new UserRankTableWrapper(MariaDBConnector.getInstance());
    }

    @Override
    public Rank getRankFrom(int userId) {
        try {
            UserRank userRank = userRankWrapper.findByUserId(userId);
            if (userRank == null) return null;
            return rankWrapper.findById(userRank.rankId);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public Rank getRankFrom(String name) {
        try {
            return rankWrapper.findByName(name);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(int userId, int rankId) {

        try {
            UserRank userRank = userRankWrapper.findByUserAndRank(userId, rankId);
            if (userRank != null) return false;
            userRank = userRankWrapper.findByUserId(userId);

            if (userRank == null) {
                userRankWrapper.create(new UserRank(userId, rankId));
            } else {
                userRank.rankId = rankId;
                userRankWrapper.update(userRank);
            }
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }


        return false;
    }

    @Override
    public boolean create(Rank value) {
        try {
            rankWrapper.create(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Rank value) {
        try {
            rankWrapper.update(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int value) {
        try {
            rankWrapper.deleteById(value);
            return true;
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public Rank findById(int value) {
        try {
            return rankWrapper.findById(value);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<Rank> findAll() {
        try {
            return rankWrapper.findAll();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }
}
