package com.github.sbouclier.stockmarketmicroservices.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockPrice {
    private String isin;
    private BigDecimal price;
    private LocalDateTime time;

    private StockPrice() {
        // no-arg constructor for Jackson
    }

    public StockPrice(String isin, BigDecimal price, LocalDateTime time) {
        this.isin = isin;
        this.price = price;
        this.time = time;
    }

    public String getIsin() {
        return isin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "isin='" + isin + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
