package com.github.sbouclier.stockmarketmicroservices.task;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.sbouclier.stockmarketmicroservices.domain.StockPrice;
import com.github.sbouclier.stockmarketmicroservices.event.StockPriceCreatedEvent;

@Component
public class StockPricesGenerationScheduledTask {

    private static final Logger LOG = LoggerFactory.getLogger(StockPricesGenerationScheduledTask.class);

    @Value("${isin:FR0000000000}")
    private String isin;

    @Value("${jms.stock-prices.queue.name}")
    private String jmsQueueName;

    @Value("${jms.stock-prices.topic.name}")
    String jmsTopicName;

    private ApplicationEventPublisher applicationEventPublisher;

    private Destination jmsQueueDestination;
    private Destination jmsTopicDestination;
    private JmsTemplate jmsTemplate;

    public StockPricesGenerationScheduledTask(
            JmsTemplate jmsTemplate,
            ApplicationEventPublisher applicationEventPublisher) {
        this.jmsTemplate = jmsTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    private void init() {
        this.jmsQueueDestination = new ActiveMQQueue(jmsQueueName);
        this.jmsTopicDestination = new ActiveMQTopic(jmsTopicName);
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        final StockPrice stockPrice = new StockPrice(isin, BigDecimal.TEN, LocalDateTime.now());
        LOG.info("sending sock price: {}", stockPrice);

        jmsTemplate.convertAndSend(jmsQueueDestination, stockPrice);
        jmsTemplate.convertAndSend(jmsTopicDestination, stockPrice);

        applicationEventPublisher.publishEvent(new StockPriceCreatedEvent(this, stockPrice));
    }
}