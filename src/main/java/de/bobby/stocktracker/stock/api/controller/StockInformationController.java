package de.bobby.stocktracker.stock.api.controller;

import de.bobby.stocktracker.stock.api.model.StockInformationDto;
import de.bobby.stocktracker.stock.domain.service.StockInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockInformationController {

    private final StockInformationService stockInformationService;

    @Autowired
    public StockInformationController(StockInformationService stockInformationService) {
        this.stockInformationService = stockInformationService;
    }

    @GetMapping("/stock-information/{symbol}")
    private ResponseEntity<StockInformationDto> getStockInformation(@PathVariable String symbol) {

        StockInformationDto dto = stockInformationService.get(symbol);

        return new ResponseEntity<>(dto,
                HttpStatus.OK);
    }
}
