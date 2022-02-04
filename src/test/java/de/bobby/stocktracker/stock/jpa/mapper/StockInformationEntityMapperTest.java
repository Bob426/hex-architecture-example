package de.bobby.stocktracker.stock.jpa.mapper;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.jpa.model.StockInformationEntity;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(StockInformationEntityMapper.class)
public class StockInformationEntityMapperTest {

    private static final String NAME = "Apple";
    private static final LocalDate MINIMUM_HOLDING_TIME = LocalDate.now();
    private static final BigDecimal PURCHASE_PRICE = BigDecimal.ONE;
    private static final BigDecimal SALES_TARGET = BigDecimal.ONE;
    private static final String SYMBOL = "aapl";

    @Autowired
    StockInformationEntityMapper stockInformationEntityMapper;

    @Test
    void givenDto_WhenCallMapper_ThenReturnEntity() {
        final StockInformationDto testStockInformationDto = StockInformationDto.builder()
                .name(NAME)
                .minimumHoldingTime(MINIMUM_HOLDING_TIME)
                .purchasePrice(PURCHASE_PRICE)
                .salesTarget(SALES_TARGET)
                .symbol(SYMBOL)
                .build();

        StockInformationEntity entity = stockInformationEntityMapper.map(testStockInformationDto);

        assertThat(entity.getSymbol()).isEqualTo(SYMBOL);
        assertThat(entity.getName()).isEqualTo(NAME);
        assertThat(entity.getMinimumHoldingTime()).isEqualTo(MINIMUM_HOLDING_TIME);
        assertThat(entity.getPurchasePrice().doubleValue()).isEqualTo(PURCHASE_PRICE.doubleValue(), Offset.offset(0.0001));
        assertThat(entity.getSalesTarget().doubleValue()).isEqualTo(SALES_TARGET.doubleValue());
    }
}
