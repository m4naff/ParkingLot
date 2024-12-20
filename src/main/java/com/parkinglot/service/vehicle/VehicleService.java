package com.parkinglot.service.vehicle;

import com.parkinglot.model.Vehicle;
import com.parkinglot.model.dto.response.VehicleParkingDetailResponse;
import com.parkinglot.model.entity.VehicleEntity;
import com.parkinglot.model.dto.request.vehicle.VehicleRequest;

/**
 * Service interface named {@link VehicleService} for managing vehicles.
 */
public interface VehicleService {

    /**
     * Assigns a vehicle to a user.
     *
     * @param id             the ID of the user
     * @param vehicleRequest the vehicle details
     * @return the assigned vehicle
     */
    Vehicle assignVehicleToUser (
            String id,
            VehicleRequest vehicleRequest
    );

    /**
     * Assigns a vehicle to a user if not assigned already; otherwise, retrieves the assigned vehicle.
     *
     * @param userId         the ID of the user
     * @param vehicleRequest the vehicle details
     * @return the assigned or retrieved vehicle
     */
    Vehicle assignOrGet(final String userId, VehicleRequest vehicleRequest);

    /**
     * Retrieves a vehicle entity by its license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the vehicle entity
     */
    VehicleEntity findByLicensePlate(final String licensePlate);

    /**
     * Retrieves parking details of a vehicle by its license plate.
     *
     * @param licensePlate the license plate of the vehicle
     * @return the parking details of the vehicle
     */
    VehicleParkingDetailResponse getParkingDetails(final String licensePlate);

}
