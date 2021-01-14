package dev.dommi.gameserver.backend.plugin.database.ban;

import dev.dommi.gameserver.backend.plugin.database.user.UserTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class BanTableWrapper extends TableWrapper<Ban> {

    public static final String DB_NAME = "UserBans";

    public BanTableWrapper(Connection conn) {
        super(DB_NAME, conn);
    }

    @Override
    protected RowParser<Ban> parse() {
        return (row, conn) -> new Ban(
                row.getInt("id"),
                row.getInt("userId"),
                row.getInt("bannedById"),
                row.getString("reason"),
                row.getDate("until"),
                row.getInt("active") == 1
        );
    }

    @Override
    public void initTable() throws SQLException {
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, userId int NOT NULL, bannedById int NOT NULL, reason varchar(255) NOT NULL, until datetime NOT NULL, active tinyint(1) NOT NULL, PRIMARY KEY (id),"
                + " FOREIGN KEY (userId)  REFERENCES " + UserTableWrapper.DB_NAME + " (id),"
                + " FOREIGN KEY (bannedById)  REFERENCES " + UserTableWrapper.DB_NAME + " (id) )").execute(conn);
    }

    @Override
    public void create(Ban value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (userId, bannedById, reason, until, active) VALUES (:userId, :bannedById, :reason, :until, :active)")
                .on(Param.value("userId", value.userId), Param.value("bannedById", value.bannedById), Param.value("reason", value.reason), Param.value("until", value.until),
                        Param.value("active", value.active ? 1 : 0)).execute(conn);
    }


    public Collection<Ban> findAllByActive(boolean active) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE active = :active").on(Param.value("active", active ? 1 : 0)).as(parse().list(), conn);
    }

    public Collection<Ban> findAllByUser(int userId) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE userId = :userId").on(Param.value("userId", userId)).as(parse().list(), conn);
    }

    public Collection<Ban> findAllByDate(Date date) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE active = :active AND until > :until").on(Param.value("active", 1), Param.value(":until", date)).as(parse().list(), conn);
    }

    public Collection<Ban> findAllByUserAndDate(int userId, Date date) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE userId = :userId AND active = :active AND until > :until").on(Param.value("userId", userId), Param.value("active", 1), Param.value(":until", date)).as(parse().list(), conn);
    }

    @Override
    public void update(Ban value) throws SQLException {
        StringBuilder values = new StringBuilder();

        if (value.reason != null && !value.reason.isEmpty()) {
            values.append("reason = :reason,");
        } else if (value.until != null) {
            values.append("until = :until,");
        }
        values.append("active = :active");

        Query.of(" UPDATE " + tableName + "  SET " + values.toString() + " WHERE id = :id").on(Param.value("id", value.id), Param.value("reason", value.reason), Param.value("until", value.until),
                Param.value("active", value.active ? 1 : 0)).execute(conn);
    }
}