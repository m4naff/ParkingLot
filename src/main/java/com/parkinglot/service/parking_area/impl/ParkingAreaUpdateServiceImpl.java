package com.parkinglot.service.parking_area.impl;

import com.parkinglot.exception.parkingarea.ParkingAreaNotFoundException;
import com.parkinglot.model.ParkingArea;
import com.parkinglot.model.dto.request.parking_area.ParkingAreaUpdateRequest;
import com.parkinglot.model.entity.ParkingAreaEntity;
import com.parkinglot.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import com.parkinglot.repository.ParkingAreaRepository;
import com.parkinglot.service.parking_area.ParkingAreaUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link ParkingAreaUpdateServiceImpl} for updating parking areas.
 */
@Service
@RequiredArgsConstructor
public class ParkingAreaUpdateServiceImpl implements ParkingAreaUpdateService {

    private final ParkingAreaRepository parkingAreaRepository;

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper =
            ParkingAreaEntityToParkingAreaMapper.initialize();

    /**
     * Updates the capacity of a parking area.
     *
     * @param parkingAreaId           the ID of the parking area to update
     * @param parkingAreaUpdateRequest the request containing the new capacity
     * @return the updated parking area
     */
    @Override
    public ParkingArea parkingAreaUpdateByCapacity(
            final String parkingAreaId,
            ParkingAreaUpdateRequest parkingAreaUpdateRequest) {
        final ParkingAreaEntity parkingAreaEntityToBeUpdate = parkingAreaRepository
                .findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("ParkingAreaNotFound with given id: " + parkingAreaId));

        parkingAreaEntityToBeUpdate.setCapacity(parkingAreaUpdateRequest.getCapacity());

        parkingAreaRepository.save(parkingAreaEntityToBeUpdate);

        return parkingAreaEntityToParkingAreaMapper.map(parkingAreaEntityToBeUpdate);
    }
}
