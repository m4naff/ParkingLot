package com.parkinglot.repository;

import com.parkinglot.model.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface named {@link PriceRepository} for managing prices.
 */
public interface PriceRepository extends JpaRepository<PriceEntity, String> {
}
