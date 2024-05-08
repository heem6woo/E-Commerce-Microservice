package com.ecommerce.common;

import lombok.Getter;
import lombok.Setter;

@Getter

public enum  OrderStatus {
    PLACED, // 주문 요청됨
    ACCEPTED, // 주문 확인
    REJECTED, // 주문 거부
    CONFIRMED, // 주문 완료
    ROLLBACK_STOCK, // 재고 롤백
    ROLLBACK_PAYMENT // 페이먼트 롤백
}
