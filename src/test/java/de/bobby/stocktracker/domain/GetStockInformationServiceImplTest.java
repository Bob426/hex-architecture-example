package de.bobby.stocktracker.domain;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.domain.port.StockInformationPersistencePort;
import de.bobby.stocktracker.stock.domain.service.StockInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetStockInformationServiceImplTest {

    @InjectMocks
    private StockInformationServiceImpl stockInformationServicePort;

    @Mock
    private StockInformationPersistencePort stockInformationPersistencePort;


    @Test
    public void givenStockInformation_whenAdd_thenAddPortCalled() {
        final StockInformationDto stockInformationDto = mock(StockInformationDto.class);

        stockInformationServicePort.addStockInformation(stockInformationDto);

        verify(stockInformationPersistencePort, only()).addStockInformation(stockInformationDto);
    }

    @Test
    public void givenSymbol_whenGetStockInformationBySymbol_thenGetStockInformationBySymbolPortCalled() {
        final String symbol = "aapl";
        final StockInformationDto stockInformationDto = mock(StockInformationDto.class);

        when(stockInformationPersistencePort.getStockInformationBy(eq(symbol))).thenReturn(stockInformationDto);

        StockInformationDto dto = stockInformationServicePort.get(symbol);

        assertThat(stockInformationDto).isSameAs(dto);
        verify(stockInformationPersistencePort, only()).getStockInformationBy(symbol);


    }


}
