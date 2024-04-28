package com.ecommerce.reviewservice.grpclient;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.grpc.MemberIdServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberIdClient {
    @GrpcClient("member-service")
    private MemberIdServiceGrpc.MemberIdServiceBlockingStub client;

    public int requestMemberId(final String email) {
        var request = IdRequest.newBuilder().setEmail(email).build();
        try{
            log.info("Trying to request Id to gRPC server");
            IdReply response = this.client.getMemberId(IdRequest.newBuilder().setEmail(email).build());
            log.info("Getting response from gRPC.");
            return response.getMemberId();
        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Not found email");
        }
    }
}
