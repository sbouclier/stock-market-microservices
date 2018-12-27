package com.github.sbouclier.stockmarketmicroservices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_prices")
public class StockPriceHistory {

    @Id
    private String id;

    private String isin;
    private BigDecimal price;
    private LocalDateTime time;

    private StockPriceHistory() {
        // no-arg constructor for Jackson
    }

    public StockPriceHistory(String isin, BigDecimal price, LocalDateTime time) {
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
        return "StockPriceHistory{" +
                "isin='" + isin + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
