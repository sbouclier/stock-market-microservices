package com.github.sbouclier.stockmarketmicroservices.domain;

public class ProducerStats {

    private String isin;
    private int count;

    public ProducerStats(String isin, int count) {
        this.isin = isin;
        this.count = count;
    }

    public String getIsin() {
        return isin;
    }

    public int getCount() {
        return count;
    }
}
