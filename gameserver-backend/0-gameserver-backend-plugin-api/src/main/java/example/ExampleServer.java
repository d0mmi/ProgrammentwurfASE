package example;


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

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {

        final ExampleServer server = new ExampleServer(8080);
        server.start();
        server.blockUntilShutdown();
    }

}
