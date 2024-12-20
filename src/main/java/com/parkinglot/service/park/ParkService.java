package com.parkinglot.service.park;

import com.parkinglot.model.dto.request.park.ParkCheckInRequest;
import com.parkinglot.model.dto.request.park.ParkCheckOutRequest;
import com.parkinglot.model.dto.response.park.ParkCheckInResponse;
import com.parkinglot.model.dto.response.park.ParkCheckOutResponse;
import com.parkinglot.model.entity.ParkingAreaEntity;

/**
 * Service interface named {@link ParkService} for parking.
 */
public interface ParkService {

    /**
     * Checks in a vehicle to the parking area.
     *
     * @param userId      the ID of the user
     * @param requestBody the check-in request body
     * @return the check-in response
     */
    ParkCheckInResponse checkIn(final String userId, final ParkCheckInRequest requestBody);

    /**
     * Counts the number of current parks in the parking area.
     *
     * @param parkingAreaEntity the parking area entity
     * @return the number of current parks
     */
    Integer countCurrentParks(ParkingAreaEntity parkingAreaEntity);

    /**
     * Checks out a vehicle from the parking area.
     *
     * @param userId              the ID of the user
     * @param parkCheckOutRequest the check-out request
     * @return the check-out response
     */
    ParkCheckOutResponse checkOut(final String userId, final ParkCheckOutRequest parkCheckOutRequest);
}
