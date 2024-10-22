package com.parkinglot.service.park.impl;

import com.parkinglot.exception.parkingarea.ParkingAreaCapacityException;
import com.parkinglot.exception.parkingarea.ParkingAreaNotFoundException;
import com.parkinglot.model.Park;
import com.parkinglot.model.Vehicle;
import com.parkinglot.model.dto.request.park.ParkCheckInRequest;
import com.parkinglot.model.dto.request.park.ParkCheckOutRequest;
import com.parkinglot.model.dto.response.park.ParkCheckInResponse;
import com.parkinglot.model.dto.response.park.ParkCheckOutResponse;
import com.parkinglot.model.entity.ParkEntity;
import com.parkinglot.model.entity.ParkingAreaEntity;
import com.parkinglot.model.entity.PriceListEntity;
import com.parkinglot.model.entity.VehicleEntity;
import com.parkinglot.model.enums.ParkStatus;
import com.parkinglot.model.mapper.park.*;
import com.parkinglot.model.mapper.vehicle.VehicleToVehicleEntityMapper;
import com.parkinglot.repository.ParkRepository;
import com.parkinglot.repository.ParkingAreaRepository;
import com.parkinglot.service.park.ParkService;
import com.parkinglot.service.vehicle.VehicleService;
import com.parkinglot.utils.price.FeeCalculationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service implementation class named {@link ParkServiceImpl} for managing park operations.
 */
@Service
@RequiredArgsConstructor
public class ParkServiceImpl implements ParkService {

    private final ParkRepository parkRepository;

    private final VehicleService vehicleService;

    private final ParkCheckInRequestToParkEntityMapper parkCheckInRequestToParkEntityMapper =
            ParkCheckInRequestToParkEntityMapper.initialize();

    private final ParkEntityToParkMapper parkEntityToParkMapper =
            ParkEntityToParkMapper.initialize();

    private final ParkToParkEntityMapper parkToParkEntityMapper =
            ParkToParkEntityMapper.initialize();

    private final ParkToParkCheckInResponseMapper parkToParkCheckInResponseMapper =
            ParkToParkCheckInResponseMapper.initialize();

    private final VehicleToVehicleEntityMapper vehicleToVehicleEntityMapper =
            VehicleToVehicleEntityMapper.initialize();

    private final ParkingAreaToParkingAreaEntityMapper parkingAreaToParkingAreaEntityMapper =
            ParkingAreaToParkingAreaEntityMapper.initialize();

    private final ParkEntityToParkCheckOutResponseMapper parkEntityToParkCheckOutResponseMapper =
            ParkEntityToParkCheckOutResponseMapper.initialize();
    private final ParkingAreaRepository parkingAreaRepository;

    /**
     * Checks in a vehicle to the parking area.
     *
     * @param userId            the ID of the user
     * @param parkCheckInRequest the check-in request
     * @return the check-in response
     */
    @Override
    @Transactional
    public ParkCheckInResponse checkIn(String userId, ParkCheckInRequest parkCheckInRequest) {
        final ParkingAreaEntity existingParkingEntityArea  = parkingAreaRepository.findById(parkCheckInRequest.getParkingAreaId())
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parkingAreaId: " + parkCheckInRequest.getParkingAreaId()));

        Vehicle vehicle = vehicleService.assignOrGet(userId, parkCheckInRequest.getVehicle());
        validateAvailableCapacity(existingParkingEntityArea);
        Park park = parkAvailableArea(parkCheckInRequest, vehicle, existingParkingEntityArea);
        return parkToParkCheckInResponseMapper.map(park);
    }

    /**
     * Validates the available capacity in the parking area.
     *
     * @param parkingAreaEntity the parking area entity to check
     * @throws ParkingAreaCapacityException if the parking area capacity is full
     */
    private void validateAvailableCapacity(ParkingAreaEntity parkingAreaEntity) {
        Integer currentParks = countCurrentParks(parkingAreaEntity);
        if(parkingAreaEntity.getCapacity() <= currentParks) {
            throw new ParkingAreaCapacityException();
        }
    }

    /**
     * Parks a vehicle in the available parking area.
     *
     * @param checkInRequest           the check-in request
     * @param vehicle                  the vehicle to be parked
     * @param existingParkingEntityArea the existing parking area entity
     * @return the parked vehicle
     */
    private Park parkAvailableArea(ParkCheckInRequest checkInRequest, Vehicle vehicle, ParkingAreaEntity existingParkingEntityArea) {
        ParkEntity parkEntity = parkCheckInRequestToParkEntityMapper.map(checkInRequest);
        parkEntity.setVehicleEntity(vehicleToVehicleEntityMapper.map(vehicle));
        parkEntity.setParkStatus(ParkStatus.FULL);
        parkEntity.setCheckIn(LocalDateTime.now());
        ParkEntity savedParkEntity = parkRepository.save(parkEntity);
        return parkEntityToParkMapper.map(savedParkEntity);
    }

    /**
     * Counts the number of current parks in the parking area.
     *
     * @param parkingAreaEntity the parking area entity
     * @return the number of current parks
     */
    @Override
    public Integer countCurrentParks(ParkingAreaEntity parkingAreaEntity) {
        return parkRepository.countByParkingAreaEntityAndParkStatus(parkingAreaEntity, ParkStatus.EMPTY);
    }

    /**
     * Checks out a vehicle from the parking area.
     *
     * @param userId              the ID of the user
     * @param parkCheckOutRequest the check-out request
     * @return the check-out response
     */
    @Override
    @Transactional
    public ParkCheckOutResponse checkOut(String userId, ParkCheckOutRequest parkCheckOutRequest) {
        final ParkingAreaEntity existingParkingAreaEntity = parkingAreaRepository
                .findById(parkCheckOutRequest.getParkingAreaId())
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parkingAreaId: " + parkCheckOutRequest.getParkingAreaId()));

        final VehicleEntity existingVehicle = vehicleService.findByLicensePlate(
                parkCheckOutRequest.getVehicleRequest().getLicensePlate()
        );

        ParkEntity parkEntity = parkRepository.findTopByVehicleEntityAndParkStatusOrderByCheckInDesc(
                existingVehicle,
                ParkStatus.FULL
        ).orElseThrow(
                () -> new ParkingAreaNotFoundException("With given Parking Area Id: " + parkCheckOutRequest.getParkingAreaId())
        );

        parkEntity.setCheckOut(LocalDateTime.now());
        parkEntity.setParkStatus((ParkStatus.EMPTY));

        PriceListEntity priceListEntity = parkEntity.getParkingAreaEntity().getPriceListEntity();

        BigDecimal priceForTimeInterval =  FeeCalculationUtil.findPriceForTimeInterval(priceListEntity, parkEntity);

        parkEntity.setTotalCost(priceForTimeInterval);

        parkRepository.save(parkEntity);

        return parkEntityToParkCheckOutResponseMapper.map(parkEntity);
    }
}
