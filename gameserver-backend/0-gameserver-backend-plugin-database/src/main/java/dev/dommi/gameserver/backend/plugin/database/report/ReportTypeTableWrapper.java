package dev.dommi.gameserver.backend.plugin.database.report;

import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;

public class ReportTypeTableWrapper extends TableWrapper<ReportType> {

    public static final String DB_NAME = "ReportType";

    public ReportTypeTableWrapper(Connection conn) {
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
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, PRIMARY KEY (id) )").execute(conn);
        create(new ReportType("Bug using"));
        create(new ReportType("Hacking"));
        create(new ReportType("Offensive Behaviour"));
    }

    @Override
    public void create(ReportType value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (name) VALUES (:name)").on(Param.value("name", value.name)).execute(conn);
    }

    @Override
    public void update(ReportType value) throws SQLException {
        if (value.id >= 0 && value.name != null && !value.name.isEmpty()) {
            Query.of(" UPDATE " + tableName + "  SET name = :name WHERE id = :id").on(Param.value("id", value.id), Param.value("name", value.name)).execute(conn);
        }
    }
}
