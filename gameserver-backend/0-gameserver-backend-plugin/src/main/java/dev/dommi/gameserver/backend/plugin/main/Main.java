package dev.dommi.gameserver.backend.plugin.main;

import dev.dommi.gameserver.backend.plugin.api.server.ExampleServer;

public class Main {
    public static void main(String[] args) throws Exception {
        final ExampleServer server = new ExampleServer(50051);
        server.start();
        server.blockUntilShutdown();
    }
}
