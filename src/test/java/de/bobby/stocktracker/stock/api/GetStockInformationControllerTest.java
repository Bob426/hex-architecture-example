package de.bobby.stocktracker.stock.api;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.domain.service.StockInformationServiceImpl;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetStockInformationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StockInformationServiceImpl stockInformationServiceImpl;

    @Test
    public void get()
            throws Exception {

        //given
        String symbol = "aapl";
        StockInformationDto fakeStockInformation = new StockInformationDto(
                symbol,
                BigDecimal.valueOf(1.2),
                "Apple",
                BigDecimal.valueOf(1.2),
                LocalDate.now());
        Mockito.when(stockInformationServiceImpl.get(symbol)).thenReturn(fakeStockInformation);

        //when
        ResponseEntity<StockInformationDto> response = this.restTemplate.getForEntity("http://localhost:" + port + "/stock-information/" + symbol, StockInformationDto.class);
        StockInformationDto dto = response.getBody();

        //then
        assertAll(
                () -> assertThat(dto.getSymbol()).isEqualTo(symbol),
                () -> assertThat(dto.getName()).isEqualTo(fakeStockInformation.getName()),
                () -> assertThat(dto.getMinimumHoldingTime()).isEqualTo(fakeStockInformation.getMinimumHoldingTime()),
                () -> assertThat(dto.getPurchasePrice().doubleValue()).isEqualTo(fakeStockInformation.getPurchasePrice().doubleValue(), Offset.offset(0.0001)),
                () -> assertThat(dto.getSalesTarget().doubleValue()).isEqualTo(fakeStockInformation.getSalesTarget().doubleValue())
        );

    }
}
