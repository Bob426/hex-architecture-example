package de.bobby.stocktracker.stock.jpa.adapter;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.domain.port.StockInformationPersistencePort;
import de.bobby.stocktracker.stock.jpa.mapper.StockInformationDtoMapper;
import de.bobby.stocktracker.stock.jpa.mapper.StockInformationEntityMapper;
import de.bobby.stocktracker.stock.jpa.model.StockInformationEntity;
import de.bobby.stocktracker.stock.jpa.repository.StockInformationRepository;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {StockInformationJpaAdapter.class,
        StockInformationPersistencePort.class
})
public class StockInformationJpaAdapterTest {

    private static final String NAME = "Apple";
    private static final LocalDate MINIMUM_HOLDING_TIME = LocalDate.now();
    private static final BigDecimal PURCHASE_PRICE = BigDecimal.ONE;
    private static final BigDecimal SALES_TARGET = BigDecimal.ONE;
    private static final String SYMBOL = "aapl";


    @Autowired
    private StockInformationPersistencePort stockInformationPersistencePort;

    @MockBean
    private StockInformationEntityMapper mockStockInformationEntityMapper;

    @MockBean
    private StockInformationDtoMapper mockStockInformationDtoMapper;

    @MockBean
    private StockInformationRepository mockStockInformationRepository;

    @Captor
    private ArgumentCaptor<StockInformationEntity> stockInformationEntityArgumentCaptor;

    @Test
    void givenStockInformation_whenAddStockInformation_thenEntityIsPortedToRepository() {

        final StockInformationDto testStockInformationDto = StockInformationDto.builder()
                .name(NAME)
                .minimumHoldingTime(MINIMUM_HOLDING_TIME)
                .purchasePrice(PURCHASE_PRICE)
                .salesTarget(SALES_TARGET)
                .symbol(SYMBOL)
                .build();

        when(mockStockInformationEntityMapper.map(eq(testStockInformationDto))).thenReturn(getStockInformationEntity());


        stockInformationPersistencePort.addStockInformation(testStockInformationDto);


        verify(mockStockInformationRepository, only()).save(stockInformationEntityArgumentCaptor.capture());
        final StockInformationEntity stockInformationEntity = stockInformationEntityArgumentCaptor.getValue();
        assertThat(testStockInformationDto.getSymbol()).isEqualTo(stockInformationEntity.getSymbol());
        assertThat(testStockInformationDto.getName()).isEqualTo(stockInformationEntity.getName());
        assertThat(testStockInformationDto.getMinimumHoldingTime()).isEqualTo(stockInformationEntity.getMinimumHoldingTime());
        assertThat(testStockInformationDto.getPurchasePrice().doubleValue()).isEqualTo(stockInformationEntity.getPurchasePrice().doubleValue(), Offset.offset(0.0001));
        assertThat(testStockInformationDto.getSalesTarget().doubleValue()).isEqualTo(stockInformationEntity.getSalesTarget().doubleValue());
    }

    @Test
    void givenExistingSymbol_whenCallingGetStockInformationBySymbol_thenFindBySymbolToRepository() {

        when(mockStockInformationRepository.findBySymbol(eq(SYMBOL))).thenReturn(getStockInformationEntity());
        when(mockStockInformationDtoMapper.map(getStockInformationEntity())).thenReturn(StockInformationDto.builder()
                .name(NAME)
                .minimumHoldingTime(MINIMUM_HOLDING_TIME)
                .purchasePrice(PURCHASE_PRICE)
                .salesTarget(SALES_TARGET)
                .symbol(SYMBOL)
                .build());

        final StockInformationDto dto = stockInformationPersistencePort.getStockInformationBy(SYMBOL);

        verify(mockStockInformationRepository, only()).findBySymbol(SYMBOL);
        assertThat(dto.getSymbol()).isEqualTo(SYMBOL);
        assertThat(dto.getName()).isEqualTo(NAME);
        assertThat(dto.getMinimumHoldingTime()).isEqualTo(MINIMUM_HOLDING_TIME);
        assertThat(dto.getPurchasePrice().doubleValue()).isEqualTo(PURCHASE_PRICE.doubleValue(), Offset.offset(0.0001));
        assertThat(dto.getSalesTarget().doubleValue()).isEqualTo(SALES_TARGET.doubleValue());


    }

    private StockInformationEntity getStockInformationEntity() {
        return StockInformationEntity
                .builder()
                .salesTarget(SALES_TARGET)
                .symbol(SYMBOL)
                .name(NAME)
                .minimumHoldingTime(MINIMUM_HOLDING_TIME)
                .purchasePrice(PURCHASE_PRICE)
                .build();
    }


}
