package de.bobby.stocktracker.stock.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockInformationDto {
    String symbol;
    Number salesTarget;
    String name;
    Number purchasePrice;
    LocalDate minimumHoldingTime;
}
