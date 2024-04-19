package com.ecommerce.memberservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seller_detail")
public class SellerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "licence")
    private long licence;

    @Column(name = "address")
    private String address;

    @Column(name = "domain")
    private String domain;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade =  {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "member_id")
    private Member member;
}
