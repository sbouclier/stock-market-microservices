package com.github.sbouclier.stockmarketmicroservices.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sbouclier.stockmarketmicroservices.domain.ProducerStats;
import com.github.sbouclier.stockmarketmicroservices.service.ProducerStatsService;

@RestController
@RequestMapping(value = "/api/stocks/producer/stats")
public class StockPricesProducerController {

    private static final Logger LOG = LoggerFactory.getLogger(StockPricesProducerController.class);

    private ProducerStatsService producerStatsService;

    public StockPricesProducerController(ProducerStatsService producerStatsService) {
        this.producerStatsService = producerStatsService;
    }

    @GetMapping
    public ResponseEntity<ProducerStats> getStats() {
        LOG.debug("getStats()");
        return new ResponseEntity(producerStatsService.getStats(), HttpStatus.OK);
    }

}
