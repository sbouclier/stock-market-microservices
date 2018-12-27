package com.github.sbouclier.stockmarketmicroservices.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.github.sbouclier.stockmarketmicroservices.domain.StockPrice;
import com.github.sbouclier.stockmarketmicroservices.domain.model.StockPriceHistory;
import com.github.sbouclier.stockmarketmicroservices.domain.repository.StockPriceHistoryRepository;

@Component
public class StockPricesConsumer {

    private static final Logger log = LoggerFactory.getLogger(StockPricesConsumer.class);

    private StockPriceHistoryRepository stockPriceHistoryRepository;

    public StockPricesConsumer(StockPriceHistoryRepository stockPriceHistoryRepository) {
        this.stockPriceHistoryRepository = stockPriceHistoryRepository;
    }

    @JmsListener(destination = "${jms.stock-prices.queue.name}")
    public void receiveMessage(StockPrice stockPrice) {
        log.info("Received {}", stockPrice);

        StockPriceHistory stockPriceHistory = new StockPriceHistory(
                stockPrice.getIsin(),
                stockPrice.getPrice(),
                stockPrice.getTime());
        stockPriceHistoryRepository.save(stockPriceHistory);
    }

}
