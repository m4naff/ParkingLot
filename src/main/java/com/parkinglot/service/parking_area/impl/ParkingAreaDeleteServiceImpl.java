package com.parkinglot.service.parking_area.impl;

import com.parkinglot.exception.parkingarea.ParkingAreaNotFoundException;
import com.parkinglot.model.entity.ParkingAreaEntity;
import com.parkinglot.repository.ParkingAreaRepository;
import com.parkinglot.service.parking_area.ParkingAreaDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link ParkingAreaDeleteServiceImpl} for deleting parking areas.
 */
@Service
@RequiredArgsConstructor
public class ParkingAreaDeleteServiceImpl implements ParkingAreaDeleteService {

    private final ParkingAreaRepository parkingAreaRepository;

    /**
     * Deletes a parking area by its ID.
     *
     * @param parkingAreaId the ID of the parking area to delete
     */
    @Override
    public void deleteParkingAreById(String parkingAreaId) {
        final ParkingAreaEntity parkingAreaEntityToBeDelete = parkingAreaRepository.findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parking AreaId: " + parkingAreaId));

        parkingAreaRepository.delete(parkingAreaEntityToBeDelete);
    }
}
