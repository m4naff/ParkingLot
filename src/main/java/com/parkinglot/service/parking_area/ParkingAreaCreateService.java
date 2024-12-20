package com.parkinglot.service.parking_area;

import com.parkinglot.model.ParkingArea;
import com.parkinglot.model.dto.request.parking_area.ParkingAreaCreateRequest;

/**
 * Service interface named {@link ParkingAreaCreateService} for creating parking areas.
 */
public interface ParkingAreaCreateService {

    /**
     * Creates a new parking area.
     *
     * @param parkingAreaCreateRequest the request containing details for creating the parking area
     * @return the created parking area
     */
    ParkingArea createParkingArea(final ParkingAreaCreateRequest parkingAreaCreateRequest);

}
