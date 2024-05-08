package com.ecommerce.itemservice.kafka.consumer;


import com.ecommerce.common.Order;
import com.ecommerce.common.OrderStatus;
import com.ecommerce.itemservice.kafka.producer.StockProducer;
import com.ecommerce.itemservice.service.ItemStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//
//PLACED, // 주문 요청됨
//ACCEPTED, // 주문 확인
//REJECTED, // 주문 거부
//CONFIRMED, // 주문 완료
//ROLLBACK_STOCK, // 재고 롤백

@Service
@Slf4j
public class OrderConsumer {
    @Autowired
    ItemStockService itemStockService;
    @Autowired
    StockProducer stockProducer;



    @KafkaListener(id = "order", topics = "ORDERS", groupId = "stock")
    public void onEvent(Order order) {

        //Boolean flag = itemStockService.decrease(order.getItemId(),order.getSellerId(),order.getItemQuantity());
        // for test
        if(order.getStatus() == OrderStatus.PLACED) {
            Boolean flag = true;
            //itemStockService.decrease(order.getItemId(),order.getSellerId(),order.getItemQuantity());
            if(flag){
                order.setStatus(OrderStatus.ACCEPTED);
                stockProducer.send(order);
            }
            else {
                order.setStatus(OrderStatus.REJECTED);
                stockProducer.send(order);
            }
            log.info("주문번호 " + order.getId() + " 주문 확인");
        }
        if(order.getStatus() == OrderStatus.CONFIRMED) {
            log.info("주문번호 " + order.getId() + " 주문 완료");
        }
        if(order.getStatus() == OrderStatus.ROLLBACK_STOCK) {
            log.info("주문번호 " + order.getId() + "재고 롤백");
        }



    }
}
