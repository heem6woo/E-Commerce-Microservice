/*
package com.ecommerce.customerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    private String accessToken;

    private String refreshToken;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}

 */