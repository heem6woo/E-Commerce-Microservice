package com.ecommerce.itemservice.grpcservice;

import com.ecommerce.grpc.IdReply;
import com.ecommerce.grpc.IdRequest;
import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.service.ItemSearchingService;
import com.ecommerce.itemservice.service.ItemService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.ecommerce.grpc.IdServiceGrpc;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ItemServiceGrpcServer extends IdServiceGrpc.IdServiceImplBase {

        private final ItemSearchingService itemSearchingService;

        @Override
        public void getItemInfo(final ItemRequest req, final StreamObserver<ItemReply> responseObserver) {
                int sellerId = Integer.parseInt(req.getSellerId());
                String itemName = req.getItemName();
                log.info(sellerId + " gRPC getting request");

                List<ItemDTO> itemDTOs = itemSearchingService.findByIdSalesValues(sellerId, itemName);

                if (!itemDTOs.isEmpty()) {
                        try {
                                for (ItemDTO itemDTO : itemDTOs) {
                                        ItemReply reply = new ItemReply.newBuilder()
                                                .setItemName(itemDTO.getName())
                                                .setCategoryId(itemDTO.getCategoryId())
                                                .setItemCount(itemDTO.getItemCount())
                                                .setItemPrice(itemDTO.getItemPrice())
                                                .setItemStatus(itemDTO.getItemStatus())
                                                .build();

                                        responseObserver.onNext(reply);
                                }
                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }
                }

                responseObserver.onCompleted();
        }


}
