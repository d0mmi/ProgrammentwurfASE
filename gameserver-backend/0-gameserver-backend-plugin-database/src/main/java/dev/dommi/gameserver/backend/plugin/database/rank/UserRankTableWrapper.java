package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRankTableWrapper extends TableWrapper<UserRank> {

    // UserId + RankID
    public UserRankTableWrapper(Connection conn) {
        super("UserRank", conn);
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
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, userId int REFERENCES User(id) NOT NULL, rankId int REFERENCES Rank(id) NOT NULL, PRIMARY KEY (id) )").execute(conn);
    }

    @Override
    public void create(UserRank value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (userId, rankId) VALUES (:userId, :rankId)").on(Param.value("userId", value.userId), Param.value("rankId", value.rankId)).execute(conn);
    }

    public UserRank findByUserId(int userId) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE userId = :userId").on(Param.value("userId", userId)).as(parse().singleNull(), conn);
    }

    public void deleteByUserId(int userId) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE userId = :userId").on(Param.value("userId", userId)).execute(conn);
    }

    public void deleteRankByUserId(int userId, int rankId) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE userId = :userId AND rankId = :rankId").on(Param.value("userId", userId), Param.value("rankId", rankId)).execute(conn);
    }

    @Override
    public void update(UserRank value) throws SQLException {
        StringBuilder values = new StringBuilder();

        if (value.userId >= 0) {
            values.append("userId = :userId,");
        } else if (value.rankId >= 0) {
            values.append("rankId = :rankId,");
        }

        String valueString = values.toString();
        valueString = valueString.substring(0, valueString.length() - 2);
        Query.of(" UPDATE " + tableName + "  SET " + valueString + " WHERE id = :id").on(Param.value("id", value.id), Param.value("userId", value.userId), Param.value("rankId", value.rankId)).execute(conn);
    }
}
