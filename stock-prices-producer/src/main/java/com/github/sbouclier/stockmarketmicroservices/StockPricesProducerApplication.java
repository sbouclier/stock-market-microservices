package com.github.sbouclier.stockmarketmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StockPricesProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPricesProducerApplication.class);
    }
}