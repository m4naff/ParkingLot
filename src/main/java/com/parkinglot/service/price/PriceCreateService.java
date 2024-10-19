package com.parkinglot.service.price;

import com.parkinglot.model.Price;
import com.parkinglot.model.PriceList;
import com.parkinglot.model.dto.request.price.PriceCreateRequest;

/**
 * Service interface named {@link PriceCreateService} for creating a price.
 */
public interface PriceCreateService {

    /**
     * Creates a new price.
     *
     * @param priceList the price list to which the price belongs
     * @param priceCreateRequest the request containing the price details
     * @return the created price
     */
    Price creatPrice(
            final PriceList priceList,
            final PriceCreateRequest priceCreateRequest
    );
}
