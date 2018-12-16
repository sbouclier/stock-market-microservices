package com.github.sbouclier.stockmarketmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockPricesProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPricesProducerApplication.class);
    }
}