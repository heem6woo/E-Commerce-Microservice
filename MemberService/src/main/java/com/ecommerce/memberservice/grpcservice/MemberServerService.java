package com.ecommerce.memberservice.grpcservice;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.memberservice.service.MemberService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.ecommerce.grpc.MemberIdServiceGrpc;
import org.springframework.data.crossstore.ChangeSetPersister;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class MemberServerService extends MemberIdServiceGrpc.MemberIdServiceImplBase {

    private final MemberService memberService;

    @Override
    public void getMemberId(final IdRequest req, final StreamObserver<IdReply> responseObserver) {
        String email = req.getEmail();
        log.info(email + " gRPC getting request");
        IdReply reply = null;
        try {
            reply = IdReply.newBuilder().setMemberId(memberService.findByEmail(email).getId()).build();
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}