package dev.dommi.gameserver.backend.application.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.RankRepositoryImpl;
import dev.dommi.gameserver.backend.domain.valueobjects.RankVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class GetAllRanks {
    private static final Logger logger = Logger.getLogger(GetAllRanks.class.getName());

    public Collection<RankVO> getAll() {
        try {
            return new RankRepositoryImpl().getAllRanks();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return new ArrayList<>();
    }

}
