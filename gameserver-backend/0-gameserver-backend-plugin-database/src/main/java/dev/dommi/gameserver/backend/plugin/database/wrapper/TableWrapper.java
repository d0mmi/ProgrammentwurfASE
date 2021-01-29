package dev.dommi.gameserver.backend.plugin.database.wrapper;

import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public abstract class TableWrapper<T> {

    protected String tableName;
    protected MariaDBConnector conn;

    public TableWrapper(String tableName, MariaDBConnector conn) {
        this.tableName = tableName;
        this.conn = conn;
    }

    protected abstract RowParser<T> parse();

    public abstract void initTable() throws SQLException;

    public abstract void create(T value) throws SQLException;

    public abstract void update(T value) throws SQLException;

    public T findById(int id) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE id = :id").on(Param.value("id", id)).as(parse().singleNull(), conn.getConnection());
    }

    public void deleteById(int id) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE id = :id").on(Param.value("id", id)).execute(conn.getConnection());
    }

    public Collection<T> findAll() throws SQLException {
        return Query.of("SELECT * FROM " + tableName).as(parse().list(), conn.getConnection());
    }

}
