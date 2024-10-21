package com.parkinglot.service.park.impl;

import com.parkinglot.model.dto.request.park.ParkCheckInRequest;
import com.parkinglot.model.dto.request.park.ParkCheckOutRequest;
import com.parkinglot.model.dto.response.park.ParkCheckInResponse;
import com.parkinglot.model.dto.response.park.ParkCheckOutResponse;
import com.parkinglot.model.entity.ParkingAreaEntity;
import com.parkinglot.repository.ParkRepository;
import com.parkinglot.service.park.ParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link ParkServiceImpl} for managing park operations.
 */
@Service
@RequiredArgsConstructor
public class ParkServiceImpl implements ParkService {

    private final ParkRepository parkRepository;

    @Override
    public ParkCheckInResponse checkIn(String userId, ParkCheckInRequest requestBody) {
        return null;
    }

    @Override
    public Integer countCurrentParks(ParkingAreaEntity parkingAreaEntity) {
        return 0;
    }

    @Override
    public ParkCheckOutResponse checkOut(String userId, ParkCheckOutRequest parkCheckOutRequest) {
        return null;
    }
}
