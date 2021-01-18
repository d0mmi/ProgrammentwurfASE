package dev.dommi.gameserver.backend.plugin.database.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.dommi.gameserver.backend.plugin.database.wrapper.TableWrapper;
import io.jenetics.facilejdbc.Param;
import io.jenetics.facilejdbc.Query;
import io.jenetics.facilejdbc.RowParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTableWrapper extends TableWrapper<User> {

    private static final String API_ROOT_USER = "API_ROOT_USER";
    public static final String API_ROOT_EMAIL = "API_ROOT_EMAIL";
    private static final String API_ROOT_PW = "API_ROOT_PW";
    public static final String DB_NAME = "User";

    public UserTableWrapper(Connection conn) {
        super(DB_NAME, conn);
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
        Query.of("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int NOT NULL AUTO_INCREMENT, name varchar(255) NOT NULL, email varchar(255) NOT NULL, pw varchar(255) NOT NULL, PRIMARY KEY (id) )").execute(conn);
    }

    @Override
    public void create(User value) throws SQLException {
        Query.of("INSERT INTO " + tableName + " (name, email, pw) VALUES (:name, :email, :pw)").on(Param.value("name", value.name), Param.value("email", value.email), Param.value("pw", value.pw)).execute(conn);
    }

    public User createRootUser() throws SQLException {

        String email = System.getenv(API_ROOT_EMAIL);
        if (email == null) return null;
        User rootUser = findByEmail(email);
        if (rootUser == null) {
            String name = System.getenv(API_ROOT_USER);
            String pw = System.getenv(API_ROOT_PW);
            if (name != null && pw != null) {
                pw = BCrypt.withDefaults().hashToString(Integer.parseInt(System.getenv(UserController.BCRYPT_COST)), pw.toCharArray());
                create(new User(name, email, pw));
                rootUser = findByEmail(email);
            }
        }
        return rootUser;
    }

    @Override
    public void update(User value) throws SQLException {
        StringBuilder values = new StringBuilder();
        List<Param> params = new ArrayList<>();
        params.add(Param.value("id", value.id));
        if (value.name != null && !value.name.isEmpty()) {
            values.append("name = :name,");
            params.add(Param.value("name", value.name));
        } else if (value.email != null && !value.email.isEmpty()) {
            values.append("email = :email,");
            params.add(Param.value("email", value.email));
        } else if (value.pw != null && !value.pw.isEmpty()) {
            values.append("pw = :pw,");
            params.add(Param.value("pw", value.pw));
        }

        String valueString = values.toString();
        valueString = valueString.substring(0, valueString.length() - 2);

        Query.of(" UPDATE " + tableName + "  SET " + valueString + " WHERE id = :id").on(params).execute(conn);
    }

    public User findByEmail(String email) throws SQLException {

        return Query.of("SELECT * FROM " + tableName + " WHERE email = :email").on(Param.value("email", email)).as(parse().singleNull(), conn);
    }
}
