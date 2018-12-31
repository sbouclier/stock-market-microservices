package com.github.sbouclier.stockmarketmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StockPricesConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPricesConsumerApplication.class);
    }

}