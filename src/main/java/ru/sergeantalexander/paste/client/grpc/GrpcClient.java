package ru.sergeantalexander.paste.client.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;
import ru.sergeantalexander.paste.client.GrpcServ;
import ru.sergeantalexander.paste.client.ServiceGrpc;
import ru.sergeantalexander.paste.service.PasteServiceImpl;

import java.util.logging.Logger;

@Component
public class GrpcClient {

    private static final Logger log = Logger.getLogger(PasteServiceImpl.class.getName());

    public String getPosition() {

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085").usePlaintext().build();
        ServiceGrpc.ServiceBlockingStub stub = ServiceGrpc.newBlockingStub(channel);
        GrpcServ.Request request = GrpcServ.Request.newBuilder().setName("hikari").build();
        log.info("request: " + request);
        GrpcServ.Response response = stub.getPosition(request);
        log.info("response :" + response);
        return response.getPosition();
    }

}
