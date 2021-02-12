package dev.dommi.gameserver.backend.plugin.main;

import dev.dommi.gameserver.backend.plugin.api.server.APIServer;
import dev.dommi.gameserver.backend.plugin.api.server.APIServerConfig;

public class Main {
    public static void main(String[] args) {
        final APIServer server = new APIServer(APIServerConfig.getDefaultConfig());
        server.start();
    }
}
