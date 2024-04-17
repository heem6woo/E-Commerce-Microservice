package com.ecommerce.customerservice.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

@Data
@Entity
@Table(name = "customer_detail")
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private int gender;

    @Column(name = "address")
    private String address;

    @Lazy
    @OneToOne(mappedBy = "customerDetail",
            cascade =  {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    private Customer customer;
}
