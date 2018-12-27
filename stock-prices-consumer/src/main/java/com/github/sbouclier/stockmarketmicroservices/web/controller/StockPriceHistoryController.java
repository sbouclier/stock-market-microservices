package com.github.sbouclier.stockmarketmicroservices.web.controller;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.sbouclier.stockmarketmicroservices.domain.model.StockPriceHistory;
import com.github.sbouclier.stockmarketmicroservices.domain.repository.StockPriceHistoryRepository;

@RestController
@RequestMapping(value = "/api/stocks/prices")
public class StockPriceHistoryController {

    private static final Logger LOG = LoggerFactory.getLogger(StockPriceHistoryController.class);

    private static final int MAX_PAGE_SIZE = 50;

    private StockPriceHistoryRepository stockPriceHistoryRepository;

    public StockPriceHistoryController(StockPriceHistoryRepository stockPriceHistoryRepository) {
        this.stockPriceHistoryRepository = stockPriceHistoryRepository;
    }

    @GetMapping("/{isin}")
    public ResponseEntity<List<StockPriceHistory>> getPrices(
            @PathVariable String isin,
            @PageableDefault(size = MAX_PAGE_SIZE) Pageable pageable,
            @RequestParam(required = false, defaultValue = "time") String sort,
            @RequestParam(required = false, defaultValue = "asc") String order) {
        LOG.debug("getPrices(isin={})", isin);

        final PageRequest pr = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by("asc".equals(order) ? Sort.Direction.ASC : Sort.Direction.DESC, sort)
        );

        final Page<StockPriceHistory> pricesPage = stockPriceHistoryRepository.findByIsinOrderByTimeDesc(isin, pr);

        if (pricesPage.getContent().isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            long totalPrices = pricesPage.getTotalElements();
            int nbPagePrices = pricesPage.getNumberOfElements();

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalPrices));

            if (nbPagePrices < totalPrices) {
                headers.add("first", buildPageUri(PageRequest.of(0, pricesPage.getSize()), isin));
                headers.add("last", buildPageUri(PageRequest.of(pricesPage.getTotalPages() - 1, pricesPage.getSize()), isin));

                if (pricesPage.hasNext()) {
                    headers.add("next", buildPageUri(pricesPage.nextPageable(), isin));
                }

                if (pricesPage.hasPrevious()) {
                    headers.add("prev", buildPageUri(pricesPage.previousPageable(), isin));
                }

                return new ResponseEntity<>(pricesPage.getContent(), headers, HttpStatus.PARTIAL_CONTENT);
            } else {
                return new ResponseEntity(pricesPage.getContent(), headers, HttpStatus.OK);
            }
        }
    }

    private String buildPageUri(Pageable page, String isin) {
        return fromUriString("/api/stocks/prices/" + isin)
                .query("page={page}&size={size}")
                .buildAndExpand(page.getPageNumber(), page.getPageSize())
                .toUriString();
    }
}
