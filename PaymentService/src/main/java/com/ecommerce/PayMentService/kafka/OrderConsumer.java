package com.ecommerce.PayMentService.kafka;

import com.ecommerce.PayMentService.PaymentService;
import com.ecommerce.common.Order;
import com.ecommerce.common.OrderStatus;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Random;


//PLACED, // 주문 요청됨
//ACCEPTED, // 주문 확인
//REJECTED, // 주문 거부
//CONFIRMED, // 주문 완료
//ROLLBACK_STOCK, // 재고 롤백
@Service
@Slf4j
public class OrderConsumer {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentProducer paymentProducer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @KafkaListener(id = "order", topics = "ORDERS", groupId = "payment")
    public void onEvent(Order order) {
        //Boolean flag = itemStockService.decrease(order.getItemId(),order.getSellerId(),order.getItemQuantity());
        // for test
        if(order.getStatus().equals(OrderStatus.PLACED)) {
            Random rand = new Random();

            Boolean flag = true;
            int resert = rand.nextInt(6);
//            if(resert == 4){
//                flag = false;
//                logger.info("payment failed");
//            }

            if(flag){
                order.setStatus(OrderStatus.ACCEPTED);
                paymentProducer.send(order);
            } else {
                log.info("주문번호" + order.getId()+"주문 거부됨");
                order.setStatus(OrderStatus.REJECTED);
                paymentProducer.send(order);
            }
            log.info("주문번호" + order.getId()+"주문 확인");
        }
        if(order.getStatus().equals(OrderStatus.CONFIRMED)) {
            log.info("주문번호" + order.getId()+"주문 완료");
        }
        if(order.getStatus().equals(OrderStatus.ROLLBACK_PAYMENT)) {
            log.info("주문번호"  + order.getId() +"결제 롤백");
        }
    }
}
