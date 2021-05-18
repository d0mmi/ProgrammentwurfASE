package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.UserRank;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;
import dev.dommi.gameserver.backend.plugin.database.user.UserTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRankTableWrapper extends TableWrapper<UserRank> {

    public static final String DB_NAME = "UserRank";

    public UserRankTableWrapper(MariaDBConnector conn) {
        super(DB_NAME, conn);
    }

    @Override
    protected RowParser<UserRank> parse() {
        return (row, conn) -> new UserRank(
                row.getInt("id"),
                row.getInt("userId"),
                row.getInt("rankId")
        );
    }

    @Override
    public void initTable() throws SQLException {
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName +
                " ( id int NOT NULL AUTO_INCREMENT, userId int NOT NULL, rankId int NOT NULL, PRIMARY KEY (id), FOREIGN KEY (userId)  REFERENCES " +
                UserTableWrapper.DB_NAME + " (id), FOREIGN KEY (rankId)  REFERENCES " +
                RankTableWrapper.DB_NAME + " (id) )").execute(conn.getConnection());
    }

    @Override
    public void create(UserRank value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (userId, rankId) VALUES (:userId, :rankId)").on(Param.value("userId", value.userId), Param.value("rankId", value.rankId)).execute(conn.getConnection());
    }

    public UserRank findByUserId(int userId) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE userId = :userId").on(Param.value("userId", userId)).as(parse().singleNull(), conn.getConnection());
    }

    public void deleteByUserId(int userId) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE userId = :userId").on(Param.value("userId", userId)).execute(conn.getConnection());
    }

    public void deleteRankByUserId(int userId, int rankId) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE userId = :userId AND rankId = :rankId").on(Param.value("userId", userId), Param.value("rankId", rankId)).execute(conn.getConnection());
    }
}
