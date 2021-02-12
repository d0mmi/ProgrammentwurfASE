package dev.dommi.gameserver.backend.plugin.api.server;

import dev.dommi.gameserver.backend.adapter.database.ban.BanDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.ban.BanRepositoryImpl;
import dev.dommi.gameserver.backend.adapter.database.rank.RankDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.rank.RankRepositoryImpl;
import dev.dommi.gameserver.backend.adapter.database.report.ReportDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.report.ReportRepositoryImpl;
import dev.dommi.gameserver.backend.adapter.database.user.UserDatabaseController;
import dev.dommi.gameserver.backend.adapter.database.user.UserRepositoryImpl;
import dev.dommi.gameserver.backend.domain.repositories.BanRepository;
import dev.dommi.gameserver.backend.domain.repositories.RankRepository;
import dev.dommi.gameserver.backend.domain.repositories.ReportRepository;
import dev.dommi.gameserver.backend.domain.repositories.UserRepository;
import dev.dommi.gameserver.backend.plugin.api.services.ban.BanController;
import dev.dommi.gameserver.backend.plugin.api.services.login.LoginController;
import dev.dommi.gameserver.backend.plugin.api.services.rank.RankController;
import dev.dommi.gameserver.backend.plugin.api.services.report.ReportController;
import dev.dommi.gameserver.backend.plugin.api.services.user.UserController;
import dev.dommi.gameserver.backend.plugin.database.ban.BanDatabaseControllerImpl;
import dev.dommi.gameserver.backend.plugin.database.rank.RankDatabaseControllerImpl;
import dev.dommi.gameserver.backend.plugin.database.report.ReportDatabaseControllerImpl;
import dev.dommi.gameserver.backend.plugin.database.user.UserDatabaseControllerImpl;

public class APIServerConfig {
    private static final String API_PORT = "API_PORT";
    private static APIServerConfig defaultConfig;

    private int port;
    private LoginController loginController;
    private BanController banController;
    private RankController rankController;
    private ReportController reportController;
    private UserController userController;

    public APIServerConfig(int port, LoginController loginController, BanController banController, RankController rankController, ReportController reportController, UserController userController) {
        this.port = port;
        this.loginController = loginController;
        this.banController = banController;
        this.rankController = rankController;
        this.reportController = reportController;
        this.userController = userController;
    }

    public static APIServerConfig getDefaultConfig() {
        if (defaultConfig == null) {
            defaultConfig = createDefaultConfig();
        }
        return defaultConfig;
    }

    private static APIServerConfig createDefaultConfig() {
        BanDatabaseController banDatabaseController = new BanDatabaseControllerImpl();
        RankDatabaseController rankDatabaseController = new RankDatabaseControllerImpl();
        ReportDatabaseController reportDatabaseController = new ReportDatabaseControllerImpl();
        UserDatabaseController userDatabaseController = new UserDatabaseControllerImpl();

        UserRepository userRepository = new UserRepositoryImpl(userDatabaseController, rankDatabaseController);
        RankRepository rankRepository = new RankRepositoryImpl(rankDatabaseController);
        ReportRepository reportRepository = new ReportRepositoryImpl(reportDatabaseController, userRepository);
        BanRepository banRepository = new BanRepositoryImpl(banDatabaseController, userRepository);

        LoginController loginController = new LoginController(userRepository, rankRepository, banRepository);
        BanController banController = new BanController(banRepository, userRepository);
        RankController rankController = new RankController(rankRepository, userRepository);
        ReportController reportController = new ReportController(reportRepository);
        UserController userController = new UserController(userRepository);

        return new APIServerConfig(Integer.parseInt(System.getenv(API_PORT)), loginController, banController, rankController, reportController, userController);
    }

    public int getPort() {
        return port;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public BanController getBanController() {
        return banController;
    }

    public RankController getRankController() {
        return rankController;
    }

    public ReportController getReportController() {
        return reportController;
    }

    public UserController getUserController() {
        return userController;
    }
}
