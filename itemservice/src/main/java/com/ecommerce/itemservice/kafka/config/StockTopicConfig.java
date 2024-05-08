package com.ecommerce.itemservice.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Slf4j
@Configuration
public class StockTopicConfig {


    /*
    @Bean
    public NewTopic stock() {
        return TopicBuilder.name(String.valueOf(TopicEnum.STOCK))
                .partitions(3)
//                .compact()
                .build();
    }

     */


}