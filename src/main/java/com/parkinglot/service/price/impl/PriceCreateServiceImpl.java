package com.parkinglot.service.price.impl;

import com.parkinglot.model.Price;
import com.parkinglot.model.PriceList;
import com.parkinglot.model.dto.request.price.PriceCreateRequest;
import com.parkinglot.repository.PriceRepository;
import com.parkinglot.service.price.PriceCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link PriceCreateServiceImpl} for creating price.
 */
@Service
@RequiredArgsConstructor
public class PriceCreateServiceImpl implements PriceCreateService {

    private final PriceRepository priceRepository;

    @Override
    public Price creatPrice(PriceList priceList, PriceCreateRequest priceCreateRequest) {
        return null;
    }
}
