package com.ecommerce.reviewservice.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ReviewDto {

    private String sellerName;

    private String contains;

    private Timestamp date;

    private int score;
}
