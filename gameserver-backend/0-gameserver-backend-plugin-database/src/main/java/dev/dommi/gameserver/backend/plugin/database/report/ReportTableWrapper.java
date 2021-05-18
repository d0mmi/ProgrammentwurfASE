package dev.dommi.gameserver.backend.plugin.database.report;

import dev.dommi.gameserver.backend.adapter.database.report.Report;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;
import dev.dommi.gameserver.backend.plugin.database.user.UserTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportTableWrapper extends TableWrapper<Report> {

    public static final String DB_NAME = "Reports";

    public ReportTableWrapper(MariaDBConnector conn) {
        super(DB_NAME, conn);
    }

    @Override
    protected RowParser<Report> parse() {
        return (row, conn) -> new Report(
                row.getInt("id"),
                row.getInt("creator"),
                row.getInt("reported"),
                row.getString("reason"),
                row.getInt("typeId"),
                row.getInt("open") == 1
        );
    }

    @Override
    public void initTable() throws SQLException {
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, creator int NOT NULL, reported int NOT NULL, reason varchar(255) NOT NULL, typeId int NOT NULL, open tinyint(1) NOT NULL, PRIMARY KEY (id),"
                + " FOREIGN KEY (creator)  REFERENCES " + UserTableWrapper.DB_NAME + " (id),"
                + " FOREIGN KEY (reported)  REFERENCES " + UserTableWrapper.DB_NAME + " (id),"
                + " FOREIGN KEY (typeId)  REFERENCES " + ReportTypeTableWrapper.DB_NAME + " (id) )").execute(conn.getConnection());
    }

    @Override
    public void create(Report value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (creator, reported, reason, typeId, open) VALUES (:creator, :reported, :reason, :typeId, :open)")
                .on(Param.value("creator", value.creator), Param.value("reported", value.reported), Param.value("reason", value.reason), Param.value("typeId", value.typeId),
                        Param.value("open", value.open ? 1 : 0)).execute(conn.getConnection());
    }

    public Collection<Report> findReportsCreatedBy(int userId) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE creator = :creator").on(Param.value("creator", userId)).as(parse().list(), conn.getConnection());
    }

    public Collection<Report> findReportsFor(int userId) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE reported = :reported").on(Param.value("reported", userId)).as(parse().list(), conn.getConnection());
    }
}
