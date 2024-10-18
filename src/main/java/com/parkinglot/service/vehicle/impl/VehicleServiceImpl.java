package com.parkinglot.service.vehicle.impl;

import com.parkinglot.model.Vehicle;
import com.parkinglot.model.dto.request.vehicle.VehicleRequest;
import com.parkinglot.model.dto.response.VehicleParkingDetailResponse;
import com.parkinglot.model.entity.VehicleEntity;
import com.parkinglot.repository.VehicleRepository;
import com.parkinglot.service.vehicle.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link VehicleServiceImpl} for managing vehicle operations.
 */
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle assignVehicleToUser(String id, VehicleRequest vehicleRequest) {
        return null;
    }

    @Override
    public Vehicle assignOrGet(String userId, VehicleRequest vehicleRequest) {
        return null;
    }

    @Override
    public VehicleEntity findByLicensePlate(String licensePlate) {
        return null;
    }

    @Override
    public VehicleParkingDetailResponse getParkingDetails(String licensePlate) {
        return null;
    }
}
