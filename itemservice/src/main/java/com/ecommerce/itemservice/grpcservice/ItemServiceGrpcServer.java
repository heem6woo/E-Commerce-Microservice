package com.ecommerce.itemservice.grpcservice;

import com.ecommerce.grpc.*;
import com.ecommerce.itemservice.dto.ItemDTO;
import com.ecommerce.itemservice.service.ItemSearchingService;
import com.ecommerce.itemservice.service.ItemService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ItemServiceGrpcServer extends ItemInfoServiceGrpc.ItemInfoServiceImplBase {

        private final ItemSearchingService itemSearchingService;

        @Override
        public void getItemInfo(final ItemRequest req, final StreamObserver<ItemReply> responseObserver) {
                int sellerId = req.getSellerId();
                String itemName = req.getItemName();
                log.info(sellerId + " gRPC getting request");
                ItemReply reply = null;
                ItemDTO itemDTO = itemSearchingService.findByIdSalesValues(sellerId, itemName);
                if (itemDTO != null) {
                        try {
                                reply = ItemReply.newBuilder()
                                                .setItemId((int) itemDTO.getItemValues().getItemId())
                                                .setCategoryId(itemDTO.getItemValues().getCategory())
                                                .setItemCount(itemDTO.getSalesValues().getItemCount())
                                                .setItemPrice(itemDTO.getSalesValues().getItemPrice())
                                                .setItemStatus(itemDTO.getSalesValues().getItemStatus())
                                                .build();
                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }finally {
                                responseObserver.onNext(reply);
                        }
                }
                responseObserver.onCompleted();
        }




}
