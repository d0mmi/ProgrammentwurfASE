package example;


import dev.dommi.example.ExampleGrpc;
import dev.dommi.example.ExampleRequest;
import dev.dommi.example.ExampleResponse;
import io.grpc.stub.StreamObserver;

public class ExampleService extends ExampleGrpc.ExampleImplBase {

    @Override
    public void getValue(ExampleRequest request, StreamObserver<ExampleResponse> responseObserver) {
        responseObserver.onNext(ExampleResponse.newBuilder().setValue("test").build());
        responseObserver.onCompleted();
    }
}
