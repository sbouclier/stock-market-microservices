package com.github.sbouclier.stockmarketmicroservices.domain;

public class ProducerStats {

    private StockPrice lastPrice;
    private int count;

    public ProducerStats(StockPrice lastPrice, int count) {
        this.lastPrice = lastPrice;
        this.count = count;
    }

    public StockPrice getLastPrice() {
        return lastPrice;
    }

    public int getCount() {
        return count;
    }
}
