package dev.dommi.gameserver.backend.plugin.main;

import dev.dommi.gameserver.backend.plugin.api.server.APIServer;

public class Main {
    public static void main(String[] args) throws Exception {
        final APIServer server = new APIServer();
        server.start();
    }
}
