package com.parkinglot.service.priceList.impl;

import com.parkinglot.model.Price;
import com.parkinglot.model.PriceList;
import com.parkinglot.model.dto.request.priceLIst.PriceListCreateRequest;
import com.parkinglot.model.entity.PriceListEntity;
import com.parkinglot.model.mapper.priceList.PriceListDTOMapper;
import com.parkinglot.model.mapper.priceList.PriceListMapper;
import com.parkinglot.repository.PriceListRepository;
import com.parkinglot.service.price.PriceCreateService;
import com.parkinglot.service.priceList.PriceListCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service implementation class named {@link PriceListCreateServiceImpl} for creating price list.
 */
@Service
@RequiredArgsConstructor
public class PriceListCreateServiceImpl implements PriceListCreateService{

    private final PriceListRepository priceListRepository;
    private final PriceCreateService priceCreateService;

    /**
     * Creates a new price list.
     *
     * @param priceListCreateRequest the request containing the price list details
     * @return the created price list
     */
    @Override
    public PriceList createPriceList(final PriceListCreateRequest priceListCreateRequest) {
        PriceListEntity priceListEntityToBeSave = PriceListDTOMapper.mapForSaving(priceListCreateRequest);

        priceListRepository.save(priceListEntityToBeSave);

        final PriceList priceListDomainModel = PriceListMapper
                .toDomainModel(priceListEntityToBeSave);

        if(Objects.isNull(priceListCreateRequest.getPrices())) {
            return priceListDomainModel;
        }

        final List<Price> createdPrices = priceListCreateRequest
                .getPrices()
                .stream()
                .map(
                        priceCreateRequest ->
                                priceCreateService
                                        .creatPrice(priceListDomainModel,
                                                priceCreateRequest)
                )
                .toList();

        priceListDomainModel.setPrices(createdPrices);

        return priceListDomainModel;
    }
}
