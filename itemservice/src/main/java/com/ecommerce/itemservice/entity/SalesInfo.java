    package com.ecommerce.itemservice.entity;

    import jakarta.persistence.*;
    import lombok.*;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.util.List;

    @Entity
    @Table(name = "SALES_INFO_TB")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class SalesInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "SALES_INFO_ID")
        private int salesInfoId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ITEM_ID", nullable = false)
        private Item item;  // Item 엔티티 참조

        @Column(name = "SELLER_ID", nullable = false)
        private Integer sellerId;

        @Column(name = "ITEM_COUNT", nullable = false)
        private Integer itemCount;

        @Column(name = "ITEM_PRICE", nullable = false)
        private Integer itemPrice;

        @Column(name = "ITEM_STATUS", nullable = false)
        private Byte itemStatus;

        // standard getters and setters
        public Boolean stockCheck(Integer qty) {
            if(this.itemCount - qty < 0 ){
                return false;
            }
            return true;
        }

    }