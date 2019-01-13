package com.github.sbouclier.stockmarketmicroservices.listener;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.github.sbouclier.stockmarketmicroservices.domain.StockPrice;
import com.github.sbouclier.stockmarketmicroservices.event.StockPriceCreatedEvent;
import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

@Component
public class StockPriceCreatedEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(StockPriceCreatedEventListener.class);

    private static final String METRIC_PRICE_COUNT = "producer.price.count";
    private static final String METRIC_LAST_PRICE = "producer.last.price";

    private MeterRegistry meterRegistry;

    @Value("${isin:FR0000000000}")
    private String isin;

    private AtomicDouble meterLastPrice;
    private final AtomicInteger counter = new AtomicInteger(0);
    private StockPrice lastPrice;

    public int getValue() {
        return counter.get();
    }

    public StockPrice getLastPrice() {
        return lastPrice;
    }

    public StockPriceCreatedEventListener(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        this.meterLastPrice = meterRegistry
                .gauge("producer.last.price",
                        Arrays.asList(Tag.of("isin", isin)),
                        new AtomicDouble(0));
    }

    @Async
    @EventListener
    public void onApplicationEvent(StockPriceCreatedEvent stockPriceCreatedEvent) {
        LOG.debug("Received StockPriceCreatedEvent : {}", stockPriceCreatedEvent);
        counter.incrementAndGet();

        lastPrice = stockPriceCreatedEvent.getStockPrice();

        this.meterLastPrice.set(lastPrice.getPrice().doubleValue());

        final Counter counter = Counter.builder("producer.price.count")
                .description("indicates instance count of the price producer")
                .tags("isin", isin)
                .register(meterRegistry);
        counter.increment();

        LOG.debug("Metric {}: {}",
                METRIC_PRICE_COUNT,
                meterRegistry
                        .get(METRIC_PRICE_COUNT)
                        .counter()
                        .count());
        LOG.debug("Metric {}: {}",
                METRIC_LAST_PRICE,
                meterRegistry
                        .get(METRIC_LAST_PRICE)
                        .gauge()
                        .value());
    }

}
