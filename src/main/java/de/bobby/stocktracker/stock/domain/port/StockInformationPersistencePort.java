package de.bobby.stocktracker.stock.domain.port;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;

public interface StockInformationPersistencePort {
    void addStockInformation(StockInformationDto stockInformationDto);

    StockInformationDto getStockInformationBy(String symbol);
}
