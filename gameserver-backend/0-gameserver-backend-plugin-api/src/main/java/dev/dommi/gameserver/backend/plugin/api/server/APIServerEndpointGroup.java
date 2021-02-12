package dev.dommi.gameserver.backend.plugin.api.server;

import dev.dommi.gameserver.backend.plugin.api.auth.AppRole;
import io.javalin.apibuilder.EndpointGroup;

import java.util.Arrays;
import java.util.HashSet;

import static io.javalin.apibuilder.ApiBuilder.*;

public class APIServerEndpointGroup implements EndpointGroup {
    private static final String USER_PATH = "user";
    private static final String USERS_PATH = "users";
    private static final String USER_ID_PATH = ":userId";
    private static final String REPORT_PATH = "report";
    private static final String REPORTS_PATH = "reports";
    private static final String REPORT_TYPES_PATH = "types";
    private static final String REPORTS_ID_PATH = ":reportId";
    private static final String BANS_PATH = "bans";
    private static final String BANS_ID_PATH = ":banId";
    private static final String LOGIN_PATH = "login";
    private static final String REGISTER_PATH = "register";
    private static final String ADMIN_PATH = "admin";
    private static final String RANKS_PATH = "ranks";
    private static final String GRANT_RANK_PATH = "grant";
    private static final String REVOKE_RANK_PATH = "revoke";

    private APIServerConfig config;

    public APIServerEndpointGroup(APIServerConfig config) {
        this.config = config;
    }

    @Override
    public void addEndpoints() {
        createRegisterPath();
        createLoginPath();
        createUserPath();
        createReportPath();
        createAdminPath();
    }


    private void createRegisterPath() {
        path(REGISTER_PATH, () -> {
            post(config.getLoginController()::register, new HashSet<>(Arrays.asList(AppRole.ANYONE)));
        });
    }

    private void createLoginPath() {
        path(LOGIN_PATH, () -> {
            post(config.getLoginController()::login, new HashSet<>(Arrays.asList(AppRole.ANYONE)));
        });
    }

    private void createUserPath() {
        path(USER_PATH, () -> {
            get(config.getUserController()::getLoggedIn, new HashSet<>(Arrays.asList(AppRole.USER)));
        });
        path(USERS_PATH, () -> {
            get(config.getUserController()::getAll, new HashSet<>(Arrays.asList(AppRole.USER)));
            path(USER_ID_PATH, () -> {
                get(config.getUserController()::getOne, new HashSet<>(Arrays.asList(AppRole.USER)));
                post(config.getUserController()::update, new HashSet<>(Arrays.asList(AppRole.USER)));
                delete(config.getUserController()::delete, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
            });
        });
    }

    private void createReportPath() {
        path(REPORT_PATH, () -> {
            post(config.getReportController()::report, new HashSet<>(Arrays.asList(AppRole.USER)));
        });
    }

    private void createAdminPath() {
        path(ADMIN_PATH, () -> {
            path(RANKS_PATH, () -> {
                get(config.getRankController()::getAll, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                path(GRANT_RANK_PATH, () -> {
                    post(config.getRankController()::grant, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                });
                path(REVOKE_RANK_PATH, () -> {
                    post(config.getRankController()::revoke, new HashSet<>(Arrays.asList(AppRole.ADMINISTRATOR)));
                });
            });
            path(REPORTS_PATH, () -> {
                path(REPORT_TYPES_PATH, () -> {
                    get(config.getReportController()::getTypes, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                });
                path(REPORTS_ID_PATH, () -> {
                    get(config.getReportController()::getOne, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                    post(config.getReportController()::updateReportStatus, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                });
                get(config.getReportController()::getAll, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
            });
            path(BANS_PATH, () -> {
                path(BANS_ID_PATH, () -> {
                    get(config.getBanController()::getOne, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                    post(config.getBanController()::updateBan, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                });
                get(config.getBanController()::getAll, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
                post(config.getBanController()::ban, new HashSet<>(Arrays.asList(AppRole.MODERATOR)));
            });
        });
    }
}
