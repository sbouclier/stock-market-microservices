package com.github.sbouclier.stockmarketmicroservices.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.sbouclier.stockmarketmicroservices.domain.ProducerStats;
import com.github.sbouclier.stockmarketmicroservices.listener.StockPriceCreatedEventListener;

@Service
public class ProducerStatsService {

    @Value("${isin:FR0000000000}")
    private String isin;

    private StockPriceCreatedEventListener stockPriceCreatedEventListener;

    public ProducerStatsService(StockPriceCreatedEventListener stockPriceCreatedEventListener) {
        this.stockPriceCreatedEventListener = stockPriceCreatedEventListener;
    }

    public ProducerStats getStats() {
        return new ProducerStats(isin, stockPriceCreatedEventListener.getValue());
    }
}
