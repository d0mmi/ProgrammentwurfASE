package dev.dommi.gameserver.backend.plugin.database.connector;

import dev.dommi.gameserver.backend.plugin.database.wrapper.UserTableWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MariaDBConnector {

    private static final Logger logger = Logger.getLogger(MariaDBConnector.class.getName());
    private static final String DB_ADRESS = "DB_ADRESS";
    private static final String DB_USER = "DB_USER";
    private static final String DB_PW = "DB_PW";
    private static MariaDBConnector instance;
    private Connection connection;

    private MariaDBConnector() {
        initConnection();
        if (connection != null) {
            initDatabase();
        }
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(buildConnectionString(System.getenv(DB_ADRESS), System.getenv(DB_USER), System.getenv(DB_PW)));
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

    private void initDatabase() {
        try {
            new UserTableWrapper(connection).initTable();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private String buildConnectionString(String host, String user, String pw) {
        return "jdbc:mariadb://" + host + "/db?user=" + user + "&password=" + pw;
    }

    public static MariaDBConnector getInstance() {
        if (instance == null) instance = new MariaDBConnector();
        return instance;
    }
}
