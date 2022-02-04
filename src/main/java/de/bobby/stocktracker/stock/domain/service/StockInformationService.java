package de.bobby.stocktracker.stock.domain.service;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;

public interface StockInformationService {

    StockInformationDto get(String symbol);

    void addStockInformation(StockInformationDto dto);
}
