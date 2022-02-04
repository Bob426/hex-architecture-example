package de.bobby.stocktracker.stock.jpa.adapter;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.domain.port.StockInformationPersistencePort;
import de.bobby.stocktracker.stock.jpa.mapper.StockInformationDtoMapper;
import de.bobby.stocktracker.stock.jpa.mapper.StockInformationEntityMapper;
import de.bobby.stocktracker.stock.jpa.model.StockInformationEntity;
import de.bobby.stocktracker.stock.jpa.repository.StockInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockInformationJpaAdapter implements StockInformationPersistencePort {

    private final StockInformationRepository stockInformationRepository;
    private final StockInformationEntityMapper stockInformationEntityMapper;
    private final StockInformationDtoMapper stockInformationDtoMapper;

    @Autowired
    public StockInformationJpaAdapter(StockInformationRepository stockInformationRepository,
                                      StockInformationEntityMapper stockInformationEntityMapper,
                                      StockInformationDtoMapper stockInformationDtoMapper) {
        this.stockInformationRepository = stockInformationRepository;
        this.stockInformationEntityMapper = stockInformationEntityMapper;
        this.stockInformationDtoMapper = stockInformationDtoMapper;
    }


    @Override
    public void addStockInformation(StockInformationDto stockInformationDto) {
        StockInformationEntity entity = stockInformationEntityMapper.map(stockInformationDto);
        stockInformationRepository.save(entity);


    }

    @Override
    public StockInformationDto getStockInformationBy(String symbol) {
        return stockInformationDtoMapper.map(stockInformationRepository.findBySymbol(symbol));
    }

}
