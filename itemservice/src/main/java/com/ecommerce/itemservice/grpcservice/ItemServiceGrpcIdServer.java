package com.ecommerce.itemservice.grpcservice;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.grpc.IdServiceGrpc;
import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.dto.ItemListDTO;
import com.ecommerce.itemservice.service.ItemSearchingService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ItemServiceGrpcIdServer extends IdServiceGrpc.IdServiceImplBase {

    private final ItemSearchingService itemSearchingService;

    @Override
    public void getId(final IdRequest req, final StreamObserver<IdReply> responseObserver) {
        String req_val = "";
        if(req.hasName()) {
            req_val = req.getName();
        }
        log.info(req_val + " gRPC getting request");
        int id = -1;
        List<ItemListDTO> itemDTOList = itemSearchingService.findItemListDTOsByName(req_val);
        id = itemDTOList.get(0).getItemValues().getItemId();
        IdReply reply = IdReply.newBuilder().setId(id).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}