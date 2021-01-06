package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankTableWrapper extends TableWrapper<Rank> {

    public static final String DB_NAME = "Ranks";

    public RankTableWrapper(Connection conn) {
        super(DB_NAME, conn);
    }

    @Override
    protected RowParser<Rank> parse() {
        return (row, conn) -> new Rank(
                row.getInt("id"),
                row.getString("name"),
                row.getInt("level")
        );
    }

    @Override
    public void initTable() throws SQLException {
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
        if (!resultSet.next()) {
            Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, level int NOT NULL, PRIMARY KEY (id) )").execute(conn);
            Query.of("INSERT INTO " + tableName + " (name, level) VALUES (:name, :level)").on(Param.value("name", "User"), Param.value("level", 1)).execute(conn);
            Query.of("INSERT INTO " + tableName + " (name, level) VALUES (:name, :level)").on(Param.value("name", "Moderator"), Param.value("level", 50)).execute(conn);
            Query.of("INSERT INTO " + tableName + " (name, level) VALUES (:name, :level)").on(Param.value("name", "Admin"), Param.value("level", 100)).execute(conn);
        }
    }

    @Override
    public void create(Rank value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (name, level) VALUES (:name, :level)").on(Param.value("name", value.name), Param.value("level", value.level)).execute(conn);
    }

    public Rank findByName(String name) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE name = :name").on(Param.value("name", name)).as(parse().singleNull(), conn);
    }

    @Override
    public void update(Rank value) throws SQLException {
        StringBuilder values = new StringBuilder();

        if (value.name != null && !value.name.isEmpty()) {
            values.append("name = :name,");
        } else if (value.level >= 0) {
            values.append("level = :level,");
        }

        String valueString = values.toString();
        valueString = valueString.substring(0, valueString.length() - 2);
        Query.of(" UPDATE " + tableName + "  SET " + valueString + " WHERE id = :id").on(Param.value("id", value.id), Param.value("name", value.name), Param.value("level", value.level)).execute(conn);
    }
}
