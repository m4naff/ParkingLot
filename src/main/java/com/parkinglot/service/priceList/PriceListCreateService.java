package com.parkinglot.service.priceList;

import com.parkinglot.model.PriceList;
import com.parkinglot.model.dto.request.priceLIst.PriceListCreateRequest;

/**
 * Service interface named {@link PriceListCreateService} for creating a price list.
 */
public interface PriceListCreateService {

    /**
     * Creates a new price list.
     *
     * @param priceListCreateRequest the request containing the price list details
     * @return the created price list
     */
    PriceList createPriceList(final PriceListCreateRequest priceListCreateRequest);
}
