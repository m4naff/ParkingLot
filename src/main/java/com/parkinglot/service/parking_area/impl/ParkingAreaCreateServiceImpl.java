package com.parkinglot.service.parking_area.impl;

import com.parkinglot.model.mapper.parking_area.ParkingAreaCreateRequestToParkingAreaEntityMapper;
import com.parkinglot.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import com.parkinglot.repository.ParkingAreaRepository;
import com.parkinglot.service.priceList.PriceListCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link ParkingAreaCreateServiceImpl} for creating parking areas.
 */
@Service
@RequiredArgsConstructor
public class ParkingAreaCreateServiceImpl {

    private final ParkingAreaRepository parkingAreaRepository;
    private final PriceListCreateService priceListCreateService;

    private final ParkingAreaCreateRequestToParkingAreaEntityMapper parkingAreaCreateRequestToParkingAreaEntityMapper =
            ParkingAreaCreateRequestToParkingAreaEntityMapper.initialize();

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper  =
            ParkingAreaEntityToParkingAreaMapper.initialize();

}
