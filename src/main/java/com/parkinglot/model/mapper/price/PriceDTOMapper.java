package com.parkinglot.model.mapper.price;

import com.parkinglot.model.dto.request.price.PriceCreateRequest;
import com.parkinglot.model.entity.PriceEntity;

/**
 * Mapper class named {@link PriceDTOMapper} to map {@link PriceCreateRequest} to {@link PriceEntity} for saving.
 */
public class PriceDTOMapper {

    /**
     * Maps a {@link PriceCreateRequest} object to a {@link PriceEntity} object for saving.
     *
     * @param priceCreateRequest The {@link PriceCreateRequest} object to map.
     * @return The mapped {@link PriceEntity} object.
     */
    public static PriceEntity mapForSaving(final PriceCreateRequest priceCreateRequest) {
        return PriceEntity.builder()
                .lowerBound(priceCreateRequest.getLowerBound())
                .upperBound(priceCreateRequest.getUpperBound())
                .cost(priceCreateRequest.getCost())
                .build();
    }
}
