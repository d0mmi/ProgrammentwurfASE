package dev.dommi.gameserver.backend.plugin.api.services;


import dev.dommi.gameserver.backend.plugin.api.ExampleGrpc;
import dev.dommi.gameserver.backend.plugin.api.ExampleRequest;
import dev.dommi.gameserver.backend.plugin.api.ExampleResponse;
import io.grpc.stub.StreamObserver;

public class ExampleService extends ExampleGrpc.ExampleImplBase {

    @Override
    public void getValue(ExampleRequest request, StreamObserver<ExampleResponse> responseObserver) {
        responseObserver.onNext(ExampleResponse.newBuilder().setValue("test").build());
        responseObserver.onCompleted();
    }
}
