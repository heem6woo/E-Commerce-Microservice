package com.ecommerce.itemservice.grpcservice;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.itemservice.service.ItemSearchingService;
import com.ecommerce.itemservice.service.ItemService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.ecommerce.grpc.IdServiceGrpc;
import org.springframework.data.crossstore.ChangeSetPersister;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ItemServiceGrpcServer extends IdServiceGrpc.IdServiceImplBase {

        private final ItemSearchingService itemSearchingService;

        @Override
        public void getItemInfo(final IdRequest req, final StreamObserver<IdReply> responseObserver) {
                String sellerId = req.getsellerId();
                String itemName = req.getitemName();
                log.info(sellerId + " gRPC getting request");
                IdReply reply = null;
                try {
                        reply = IdReply.newBuilder().setId(itemSearchingService.findItemDTOsByName(itemName)).build();// 실질적인 값 반환
                } catch (ChangeSetPersister.NotFoundException e) {
                        throw new RuntimeException(e);
                }
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
        }


}
