package de.bobby.stocktracker.stock.jpa.model;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "StockInformation")
@Getter
@Setter
@ToString
public class StockInformationEntity {
    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String symbol;

    @Column
    private BigDecimal salesTarget;

    @Column
    private String name;

    @Column
    private BigDecimal purchasePrice;

    @Column
    private LocalDate minimumHoldingTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInformationEntity that = (StockInformationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(symbol, that.symbol) && Objects.equals(salesTarget, that.salesTarget) && Objects.equals(name, that.name) && Objects.equals(purchasePrice, that.purchasePrice) && Objects.equals(minimumHoldingTime, that.minimumHoldingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, salesTarget, name, purchasePrice, minimumHoldingTime);
    }
}
