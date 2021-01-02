package dev.dommi.gameserver.backend.plugin.database.wrapper;

import dev.dommi.gameserver.backend.plugin.database.user.User;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

public class UserTableWrapper extends TableWrapper<User> {
    private static final Logger logger = Logger.getLogger(UserTableWrapper.class.getName());

    public UserTableWrapper(Connection conn) {
        super("users", conn);
    }

    @Override
    protected RowParser<User> parse() {
        return (row, conn) -> new User(
                row.getInt("id"),
                row.getString("name"),
                row.getString("email"),
                row.getString("pw")
        );

    }

    @Override
    public void initTable() throws SQLException {
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, name varchar(255), email varchar(255), pw varchar(255), PRIMARY KEY (id) )").execute(conn);
    }

    @Override
    public void create(User value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (name, email, pw) VALUES (:name, :email, :pw)")
                .on(Param.value("name", value.name), Param.value("email", value.email), Param.value("pw", value.pw)).execute(conn);
    }

    @Override
    public void update(User value) throws SQLException {
        StringBuilder values = new StringBuilder();

        if (value.name != null && !value.name.isEmpty()) {
            values.append("name = :name,");
        } else if (value.email != null && !value.email.isEmpty()) {
            values.append("email = :email,");
        } else if (value.pw != null && !value.pw.isEmpty()) {
            values.append("pw = :pw,");
        }

        String valueString = values.toString();
        valueString = valueString.substring(0, valueString.length() - 2);

        Query.of(" UPDATE " + tableName + "  SET " + valueString + " WHERE id = :id")
                .on(Param.value("id", value.id), Param.value("name", value.name), Param.value("email", value.email), Param.value("pw", value.pw)).execute(conn);
    }

    @Override
    public User findById(int id) throws SQLException {

        return Query.of("SELECT * FROM " + tableName + " WHERE id = :id")
                .on(Param.value("id", id)).as(parse().singleNull(), conn);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        Query.of("DELETE FROM " + tableName + " WHERE id = :id")
                .on(Param.value("id", id)).execute(conn);
    }

    public User findByEmail(String email) throws SQLException {

        return Query.of("SELECT * FROM " + tableName + " WHERE email = :email")
                .on(Param.value("email", email)).as(parse().singleNull(), conn);
    }

    @Override
    public Collection<User> findAll() throws SQLException {
        return Query.of("SELECT * FROM " + tableName).as(parse().list(), conn);
    }
}
