package dev.dommi.gameserver.backend.plugin.database.wrapper;

import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public abstract class TableWrapper<T> {

    protected String tableName;
    protected Connection conn;

    public TableWrapper(String tableName, Connection conn) {
        this.tableName = tableName;
        this.conn = conn;
    }

    protected abstract RowParser<T> parse();

    public abstract void initTable() throws SQLException;

    public abstract void create(T value) throws SQLException;

    public abstract void update(T value) throws SQLException;

    public abstract T findById(int id) throws SQLException;

    public abstract void deleteById(int id) throws SQLException;

    public abstract Collection<T> findAll() throws SQLException;

}
