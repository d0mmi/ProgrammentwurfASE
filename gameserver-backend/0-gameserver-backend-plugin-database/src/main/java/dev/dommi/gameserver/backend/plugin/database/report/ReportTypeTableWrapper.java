package dev.dommi.gameserver.backend.plugin.database.report;

import dev.dommi.gameserver.backend.adapter.database.report.ReportType;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;
import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportTypeTableWrapper extends TableWrapper<ReportType> {

    public static final String DB_NAME = "ReportType";

    public ReportTypeTableWrapper(MariaDBConnector conn) {
        super(DB_NAME, conn);
    }

    @Override
    protected RowParser<ReportType> parse() {
        return (row, conn) -> new ReportType(
                row.getInt("id"),
                row.getString("name")
        );
    }

    @Override
    public void initTable() throws SQLException {

        DatabaseMetaData databaseMetaData = conn.getConnection().getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
        if (!resultSet.next()) {
            Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, PRIMARY KEY (id) )").execute(conn.getConnection());
            create(new ReportType("Bug using"));
            create(new ReportType("Hacking"));
            create(new ReportType("Offensive Behaviour"));
        }
    }

    public int findIdByName(String name) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE name = :name").on(Param.value("name", name)).as(parse().singleNull(), conn.getConnection()).id;
    }

    @Override
    public void create(ReportType value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (name) VALUES (:name)").on(Param.value("name", value.name)).execute(conn.getConnection());
    }

    @Override
    public void update(ReportType value) throws SQLException {
        if (value.id >= 0 && value.name != null && !value.name.isEmpty()) {
            Query.of(" UPDATE " + tableName + "  SET name = :name WHERE id = :id").on(Param.value("id", value.id), Param.value("name", value.name)).execute(conn.getConnection());
        }
    }
}
