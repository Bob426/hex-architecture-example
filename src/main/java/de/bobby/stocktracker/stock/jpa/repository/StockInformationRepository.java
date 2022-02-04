package de.bobby.stocktracker.stock.jpa.repository;

import de.bobby.stocktracker.stock.jpa.model.StockInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface StockInformationRepository extends JpaRepository<StockInformationEntity, UUID> {

    StockInformationEntity findBySymbol(String Symbol);


}
