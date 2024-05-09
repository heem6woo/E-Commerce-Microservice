package com.ecommerce.orderservice.grpclient;

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
public class SellerIdClient {
    @GrpcClient("member-service")
    private IdServiceGrpc.IdServiceBlockingStub client;

    public int requestMemberId(final String name) {
        var request = IdRequest.newBuilder().setName(name).build();
        try{
            log.info("Trying to request Seller Id to gRPC server");
            IdReply response = this.client.getId(request);
            log.info("Getting response from gRPC." + "Seller: " + name + " Seller Id: " + response.getId());
            return response.getId();
        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Not found seller");
        }
    }
}
