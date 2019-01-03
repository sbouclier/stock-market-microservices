package com.github.sbouclier.stockmarketmicroservices.event;

import org.springframework.context.ApplicationEvent;

import com.github.sbouclier.stockmarketmicroservices.domain.StockPrice;

public class StockPriceCreatedEvent extends ApplicationEvent {

    private StockPrice stockPrice;

    public StockPriceCreatedEvent(Object source, StockPrice stockPrice) {
        super(source);
        this.stockPrice = stockPrice;
    }

    public StockPrice getStockPrice() {
        return stockPrice;
    }

    @Override
    public String toString() {
        return "StockPriceCreatedEvent{" +
                "stockPrice=" + stockPrice +
                ", source=" + source +
                '}';
    }
}
