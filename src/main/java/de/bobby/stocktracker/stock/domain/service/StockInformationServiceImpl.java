package de.bobby.stocktracker.stock.domain.service;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.domain.port.StockInformationPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockInformationServiceImpl implements StockInformationService {

    private final StockInformationPersistencePort stockInformationPersistencePort;

    @Autowired
    public StockInformationServiceImpl(StockInformationPersistencePort stockInformationPersistencePort) {
        this.stockInformationPersistencePort = stockInformationPersistencePort;
    }


    @Override
    public StockInformationDto get(String symbol) {
        return stockInformationPersistencePort.getStockInformationBy(symbol);

    }

    @Override
    public void addStockInformation(StockInformationDto dto) {
        stockInformationPersistencePort.addStockInformation(dto);

    }
}
