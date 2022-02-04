package de.bobby.stocktracker.stock.jpa.mapper;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.jpa.model.StockInformationEntity;
import org.springframework.stereotype.Component;

@Component
public class StockInformationDtoMapper {

    public StockInformationDto map(StockInformationEntity stockInformationEntity) {
        return StockInformationDto.builder()
                .name(stockInformationEntity.getName())
                .minimumHoldingTime(stockInformationEntity.getMinimumHoldingTime())
                .purchasePrice(stockInformationEntity.getPurchasePrice())
                .salesTarget(stockInformationEntity.getSalesTarget())
                .symbol(stockInformationEntity.getSymbol())
                .build();

    }
}
