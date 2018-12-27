package com.github.sbouclier.stockmarketmicroservices.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.sbouclier.stockmarketmicroservices.domain.model.StockPriceHistory;

public interface StockPriceHistoryRepository extends PagingAndSortingRepository<StockPriceHistory, String> {
    Page<StockPriceHistory> findByIsinOrderByTimeDesc(String isin, Pageable pageable);
}

