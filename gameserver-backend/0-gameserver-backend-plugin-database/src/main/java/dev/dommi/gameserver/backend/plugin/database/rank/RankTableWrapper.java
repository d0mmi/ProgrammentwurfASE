package dev.dommi.gameserver.backend.plugin.database.rank;

import dev.dommi.gameserver.backend.adapter.database.rank.Rank;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;
import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RankTableWrapper extends TableWrapper<Rank> {

    public static final String DB_NAME = "Ranks";

    public RankTableWrapper(MariaDBConnector conn) {
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
        DatabaseMetaData databaseMetaData = conn.getConnection().getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
        if (!resultSet.next()) {
            Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, level int NOT NULL, PRIMARY KEY (id) )").execute(conn.getConnection());
            create(new Rank("User", 1));
            create(new Rank("Moderator", 50));
            create(new Rank("Admin", 100));
        }
    }

    @Override
    public void create(Rank value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (name, level) VALUES (:name, :level)").on(Param.value("name", value.name), Param.value("level", value.level)).execute(conn.getConnection());
    }

    public Rank findByName(String name) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE name = :name").on(Param.value("name", name)).as(parse().singleNull(), conn.getConnection());
    }
}
