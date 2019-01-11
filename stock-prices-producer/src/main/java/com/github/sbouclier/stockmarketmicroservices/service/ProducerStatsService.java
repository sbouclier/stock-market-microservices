package com.github.sbouclier.stockmarketmicroservices.service;

import org.springframework.stereotype.Service;

import com.github.sbouclier.stockmarketmicroservices.domain.ProducerStats;
import com.github.sbouclier.stockmarketmicroservices.listener.StockPriceCreatedEventListener;

@Service
public class ProducerStatsService {

    private StockPriceCreatedEventListener stockPriceCreatedEventListener;

    public ProducerStatsService(StockPriceCreatedEventListener stockPriceCreatedEventListener) {
        this.stockPriceCreatedEventListener = stockPriceCreatedEventListener;
    }

    public ProducerStats getStats() {
        return new ProducerStats(
                stockPriceCreatedEventListener.getLastPrice(),
                stockPriceCreatedEventListener.getValue());
    }
}
