package com.ecommerce.reviewservice.grpclient;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.grpc.IdServiceGrpc;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemIdClient {
    @GrpcClient("item-service")
    private IdServiceGrpc.IdServiceBlockingStub client;

    public int requestItemId(final String itemName) {
        var request = IdRequest.newBuilder().setName(itemName).build();
        try{
            log.info("Trying to request Id to gRPC server");
            IdReply response = this.client.getId(request);
            log.info("Getting response from gRPC.");
            return response.getId();
        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Not found email");
        }
    }
}
