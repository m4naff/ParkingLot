package com.parkinglot.service.price.impl;

import com.parkinglot.model.Price;
import com.parkinglot.model.PriceList;
import com.parkinglot.model.dto.request.price.PriceCreateRequest;
import com.parkinglot.model.entity.PriceEntity;
import com.parkinglot.model.mapper.price.PriceDTOMapper;
import com.parkinglot.model.mapper.price.PriceMapper;
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

    /**
     * Creates a new price.
     *
     * @param priceList the price list to which the price belongs
     * @param priceCreateRequest the request containing the price details
     * @return the created price
     */
    @Override
    public Price creatPrice(final PriceList priceList, final PriceCreateRequest priceCreateRequest) {
        PriceEntity priceEntityToBeSave = PriceDTOMapper.mapForSaving(priceCreateRequest);
        priceRepository.save(priceEntityToBeSave);
        return PriceMapper.toDomainModel(priceEntityToBeSave);
    }
}
