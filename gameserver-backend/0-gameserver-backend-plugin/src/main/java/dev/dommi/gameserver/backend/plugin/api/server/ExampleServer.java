package dev.dommi.gameserver.backend.plugin.api.server;


import dev.dommi.gameserver.backend.plugin.api.services.ExampleService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExampleServer {
    private static final Logger logger = Logger.getLogger(ExampleServer.class.getName());
    private int port;
    private Server server;

    public ExampleServer(int port) {
        this.port = port;
        server = ServerBuilder.forPort(port).addService(new ExampleService())
                .build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);

    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the dev.dommi.gameserver.backend.plugin.main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
