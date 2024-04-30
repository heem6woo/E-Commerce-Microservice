package com.ecommerce.memberservice.grpcservice;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.memberservice.service.MemberService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.ecommerce.grpc.IdServiceGrpc;
import org.springframework.data.crossstore.ChangeSetPersister;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class MemberServerService extends IdServiceGrpc.IdServiceImplBase {

    private final MemberService memberService;

    @Override
    public void getId(final IdRequest req, final StreamObserver<IdReply> responseObserver) {
        String req_val;
        if(req.hasEmail()) {
            req_val = req.getEmail();
        } else {
            req_val = req.getName();
        }
        log.info(req_val + " gRPC getting request");
        int id = 0;
        if (req.hasEmail()) {
            try {
                id = memberService.findByEmail(req_val).getId();
            } catch (ChangeSetPersister.NotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            id = memberService.findByName(req_val).getId();
        }
        IdReply reply = IdReply.newBuilder().setId(id).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}