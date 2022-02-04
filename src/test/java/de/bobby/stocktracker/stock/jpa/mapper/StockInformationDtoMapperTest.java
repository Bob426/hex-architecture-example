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

@SpringJUnitConfig(StockInformationDtoMapper.class)
public class StockInformationDtoMapperTest {

    private static final String NAME = "Apple";
    private static final LocalDate MINIMUM_HOLDING_TIME = LocalDate.now();
    private static final BigDecimal PURCHASE_PRICE = BigDecimal.ONE;
    private static final BigDecimal SALES_TARGET = BigDecimal.ONE;
    private static final String SYMBOL = "aapl";

    @Autowired
    private StockInformationDtoMapper stockInformationDtoMapper;

    @Test
    void givenEntity_WhenCallMapper_thenReturnDto() {
        final StockInformationEntity testEntity = StockInformationEntity
                .builder()
                .salesTarget(SALES_TARGET)
                .symbol(SYMBOL)
                .name(NAME)
                .minimumHoldingTime(MINIMUM_HOLDING_TIME)
                .purchasePrice(PURCHASE_PRICE)
                .build();

        StockInformationDto dto = stockInformationDtoMapper.map(testEntity);

        assertThat(dto.getSymbol()).isEqualTo(SYMBOL);
        assertThat(dto.getName()).isEqualTo(NAME);
        assertThat(dto.getMinimumHoldingTime()).isEqualTo(MINIMUM_HOLDING_TIME);
        assertThat(dto.getPurchasePrice().doubleValue()).isEqualTo(PURCHASE_PRICE.doubleValue(), Offset.offset(0.0001));
        assertThat(dto.getSalesTarget().doubleValue()).isEqualTo(SALES_TARGET.doubleValue());

    }
}
