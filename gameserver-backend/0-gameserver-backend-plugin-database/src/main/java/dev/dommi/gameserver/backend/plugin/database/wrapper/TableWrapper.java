package dev.dommi.gameserver.backend.plugin.database.wrapper;

import dev.dommi.gameserver.backend.adapter.database.DatabaseObject;
import dev.dommi.gameserver.backend.adapter.database.ban.Ban;
import dev.dommi.gameserver.backend.plugin.database.connector.MariaDBConnector;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class TableWrapper<T extends DatabaseObject> {

    protected String tableName;
    protected MariaDBConnector conn;

    public TableWrapper(String tableName, MariaDBConnector conn) {
        this.tableName = tableName;
        this.conn = conn;
    }

    protected abstract RowParser<T> parse();

    public abstract void initTable() throws SQLException;

    public abstract void create(T value) throws SQLException;

    public void update(DatabaseObject value) throws SQLException{
            buildUpdateSQLQuery(value.getParamNames(), value.getValues()).execute(conn.getConnection());
    }

    public T findById(int id) throws SQLException {
        return Query.of("SELECT * FROM " + tableName + " WHERE id = :id").on(Param.value("id", id)).as(parse().singleNull(), conn.getConnection());
    }

    public void deleteById(int id) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE id = :id").on(Param.value("id", id)).execute(conn.getConnection());
    }

    public Collection<T> findAll() throws SQLException {
        return Query.of("SELECT * FROM " + tableName).as(parse().list(), conn.getConnection());
    }

    protected Query buildUpdateSQLQuery(List<String> paramNames, List<Object> values) {
        if (paramNames.size() != values.size())
            throw new IllegalArgumentException("paramNames and values need to have the same amount of elements!");
        StringBuilder valuesSB = new StringBuilder();
        List<Param> params = new ArrayList<>();

        for (int i = 0; i < paramNames.size(); i++) {

            String paramName = paramNames.get(i);
            Object value = values.get(i);

            params.add(Param.value(paramName, value));

            valuesSB.append(paramName);
            valuesSB.append(" = :");
            valuesSB.append(paramName);

            if (i != paramNames.size() - 1) {
                valuesSB.append(",");
            }
        }
        return Query.of(" UPDATE " + tableName + "  SET " + valuesSB.toString() + " WHERE id = :id").on(params);
    }

}
