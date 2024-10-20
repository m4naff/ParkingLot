package com.parkinglot.service.parking_area.impl;

import com.parkinglot.exception.parkingarea.ParkingAreaAlreadyExistException;
import com.parkinglot.model.ParkingArea;
import com.parkinglot.model.PriceList;
import com.parkinglot.model.dto.request.parking_area.ParkingAreaCreateRequest;
import com.parkinglot.model.entity.ParkingAreaEntity;
import com.parkinglot.model.mapper.parking_area.ParkingAreaCreateRequestToParkingAreaEntityMapper;
import com.parkinglot.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import com.parkinglot.model.mapper.priceList.PriceListMapper;
import com.parkinglot.repository.ParkingAreaRepository;
import com.parkinglot.service.parking_area.ParkingAreaCreateService;
import com.parkinglot.service.priceList.PriceListCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service implementation class named {@link ParkingAreaCreateServiceImpl} for creating parking areas.
 */
@Service
@RequiredArgsConstructor
public class ParkingAreaCreateServiceImpl implements ParkingAreaCreateService {

    private final ParkingAreaRepository parkingAreaRepository;
    private final PriceListCreateService priceListCreateService;

    private final ParkingAreaCreateRequestToParkingAreaEntityMapper parkingAreaCreateRequestToParkingAreaEntityMapper =
            ParkingAreaCreateRequestToParkingAreaEntityMapper.initialize();

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper  =
            ParkingAreaEntityToParkingAreaMapper.initialize();

    /**
     * Creates a new parking area.
     *
     * @param parkingAreaCreateRequest the request containing details for creating the parking area
     * @return the created parking area
     */
    @Override
    public ParkingArea createParkingArea(final ParkingAreaCreateRequest parkingAreaCreateRequest) {
        this.checkParkingAreaNameAndLocationUniqueness(
                parkingAreaCreateRequest.getName(),
                parkingAreaCreateRequest.getLocation()
        );

        ParkingAreaEntity parkingAreaToBeCreate = parkingAreaCreateRequestToParkingAreaEntityMapper
                .map(parkingAreaCreateRequest);
        parkingAreaRepository.save(parkingAreaToBeCreate);

        if(Objects.nonNull(parkingAreaCreateRequest.getPriceListRequest())) {
            final PriceList createdPriceListForParkingArea = priceListCreateService
                    .createPriceList(parkingAreaCreateRequest.getPriceListRequest());

            parkingAreaToBeCreate.setPriceListEntity(PriceListMapper.toEntity(createdPriceListForParkingArea));
        }
        return parkingAreaEntityToParkingAreaMapper
                .map(parkingAreaToBeCreate);
    }

    /**
     * Checks if a parking area with the given name and location already exists.
     *
     * @param name the name of the parking area to check
     * @param location the location of the parking area to check
     * @throws ParkingAreaAlreadyExistException if a parking area with the same name and location already exists
     */
    private void checkParkingAreaNameAndLocationUniqueness(
            final String name,
            final String location) {
        if(Boolean.TRUE.equals(parkingAreaRepository
                .existsParkingAreaEntitiesByNameAndLocation(name, location))) {
            throw new ParkingAreaAlreadyExistException();
        }
    }
}
