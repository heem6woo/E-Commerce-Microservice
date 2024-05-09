package com.ecommerce.orderservice.grpclient;

import com.ecommerce.grpc.*;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemInfoClient {
    @GrpcClient("item-service")
    private ItemInfoServiceGrpc.ItemInfoServiceBlockingStub client;

    public ItemReply requestItemInfo(final int sellerId, final String name) {

        var request = ItemRequest.newBuilder().setSellerId(sellerId).setItemName(name).build();
        try{
            log.info("Trying to request Item Information to gRPC server");
            ItemReply response = this.client.getItemInfo(request);
            log.info("Getting response from gRPC.");
            return response;
        } catch (StatusRuntimeException e) {
            throw new RuntimeException("Not found item");
        }
    }
}
