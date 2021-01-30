package dev.dommi.gameserver.backend.plugin.database.connector;

import dev.dommi.gameserver.backend.plugin.database.ban.BanTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.rank.RankTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.rank.UserRank;
import dev.dommi.gameserver.backend.plugin.database.rank.UserRankTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.report.ReportTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.report.ReportTypeTableWrapper;
import dev.dommi.gameserver.backend.plugin.database.user.User;
import dev.dommi.gameserver.backend.plugin.database.user.UserTableWrapper;
import io.jenetics.facilejdbc.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class MariaDBConnector {

    private static final Logger logger = Logger.getLogger(MariaDBConnector.class.getName());
    private static final String DB = "db";
    private static final String DB_ADRESS = "DB_ADRESS";
    private static final String DB_USER = "DB_USER";
    private static final String DB_PW = "DB_PW";
    private static final String ROOT_RANK = "Admin";
    private static MariaDBConnector instance;
    private Connection connection;

    private MariaDBConnector() {
        init();
    }

    private void init() {
        initConnection();
        if (connection != null) {
            initDatabase();
        }
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(buildConnectionString(System.getenv(DB_ADRESS), System.getenv(DB_USER), System.getenv(DB_PW), false));
            Query.of("CREATE DATABASE IF NOT EXISTS " + DB).execute(connection);
            connection.close();
            connect();
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(buildConnectionString(System.getenv(DB_ADRESS), System.getenv(DB_USER), System.getenv(DB_PW), true));
    }

    private void initDatabase() {
        try {
            UserTableWrapper userTableWrapper = new UserTableWrapper(this);
            userTableWrapper.initTable();
            RankTableWrapper rankTableWrapper = new RankTableWrapper(this);
            rankTableWrapper.initTable();
            UserRankTableWrapper userRankTableWrapper = new UserRankTableWrapper(this);
            userRankTableWrapper.initTable();

            User root = userTableWrapper.createRootUser();
            if (root != null) {
                int rankId = rankTableWrapper.findByName(ROOT_RANK).id;
                userRankTableWrapper.create(new UserRank(root.id, rankId));
            }

            new ReportTypeTableWrapper(this).initTable();
            new ReportTableWrapper(this).initTable();

            new BanTableWrapper(this).initTable();

        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null) {
                init();
            } else if (connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
        return connection;
    }

    private String buildConnectionString(String host, String user, String pw, boolean withDB) {
        return "jdbc:mariadb://" + host + "/" + (withDB ? DB : "") + "?user=" + user + "&password=" + pw;
    }

    public static MariaDBConnector getInstance() {
        if (instance == null) instance = new MariaDBConnector();
        return instance;
    }
}
