package com.github.sbouclier.stockmarketmicroservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.github.sbouclier.stockmarketmicroservices.domain.StockPrice;

@Component
public class StockPricesConsumer {

    private static final Logger log = LoggerFactory.getLogger(StockPricesConsumer.class);

    @JmsListener(destination = "${jms.stock-prices.queue.name}")
    public void receiveMessage(StockPrice stockPrice) {
        log.info("Received {}", stockPrice);
    }

}
