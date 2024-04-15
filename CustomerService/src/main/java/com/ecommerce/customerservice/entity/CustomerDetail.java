package com.ecommerce.customerservice.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customerdetail")
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
}
