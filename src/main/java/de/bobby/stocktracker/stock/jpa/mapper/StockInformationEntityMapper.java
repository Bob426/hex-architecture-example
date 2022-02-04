package de.bobby.stocktracker.stock.jpa.mapper;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.jpa.model.StockInformationEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StockInformationEntityMapper {

    public StockInformationEntity map(StockInformationDto stockInformationDto) {
        return StockInformationEntity.builder()
                .minimumHoldingTime(stockInformationDto.getMinimumHoldingTime())
                .name(stockInformationDto.getName())
                .purchasePrice(BigDecimal.valueOf(stockInformationDto.getPurchasePrice().doubleValue()))
                .salesTarget(BigDecimal.valueOf(stockInformationDto.getSalesTarget().doubleValue()))
                .symbol(stockInformationDto.getSymbol())
                .build();

    }
}
