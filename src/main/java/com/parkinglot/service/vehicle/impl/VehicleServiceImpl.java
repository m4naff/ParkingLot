package com.parkinglot.service.vehicle.impl;

import com.parkinglot.model.Vehicle;
import com.parkinglot.model.dto.request.vehicle.VehicleRequest;
import com.parkinglot.model.dto.response.VehicleParkingDetailResponse;
import com.parkinglot.model.entity.VehicleEntity;
import com.parkinglot.model.mapper.park.ParkEntityToParkDetailResponse;
import com.parkinglot.model.mapper.vehicle.VehicleEntityToVehicleMapper;
import com.parkinglot.model.mapper.vehicle.VehicleRequestToVehicleMapper;
import com.parkinglot.model.mapper.vehicle.VehicleToVehicleEntityMapper;
import com.parkinglot.repository.VehicleRepository;
import com.parkinglot.service.auth.UserService;
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

    private final UserService userService;

    private final VehicleToVehicleEntityMapper vehicleToVehicleEntityMapper =
            VehicleToVehicleEntityMapper.initialize();

    private final ParkEntityToParkDetailResponse parkEntityToParkDetailResponse =
            ParkEntityToParkDetailResponse.initialize();

    private final VehicleRequestToVehicleMapper vehicleRequestToVehicleMapper =
            VehicleRequestToVehicleMapper.initialize();

    private final VehicleEntityToVehicleMapper vehicleEntityToVehicleMapper =
            VehicleEntityToVehicleMapper.initialize();

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
