package com.parkinglot.service.parking_area.impl;

import com.parkinglot.exception.parkingarea.DailyIncomeException;
import com.parkinglot.exception.parkingarea.InvalidDateException;
import com.parkinglot.exception.parkingarea.ParkingAreaNotFoundException;
import com.parkinglot.model.ParkingArea;
import com.parkinglot.model.dto.response.parkingarea.ParkingAreaIncomeResponse;
import com.parkinglot.model.entity.ParkingAreaEntity;
import com.parkinglot.model.mapper.parking_area.ParkingAreaEntityToParkingAreaMapper;
import com.parkinglot.repository.ParkingAreaRepository;
import com.parkinglot.service.parking_area.ParkingAreaGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Service implementation class named {@link ParkingAreaGetServiceImpl} for getting parking areas.
 */
@Service
@RequiredArgsConstructor
public class ParkingAreaGetServiceImpl implements ParkingAreaGetService {

    private final ParkingAreaRepository parkingAreaRepository;

    private final ParkingAreaEntityToParkingAreaMapper parkingAreaEntityToParkingAreaMapper =
            ParkingAreaEntityToParkingAreaMapper.initialize();

    /**
     * Retrieves a parking area by its ID.
     *
     * @param parkingAreaId the ID of the parking area to retrieve
     * @return the retrieved parking area
     */
    @Override
    public ParkingArea getParkingAreaById(final String parkingAreaId) {
        final ParkingAreaEntity existingParkingArea = parkingAreaRepository.findById(parkingAreaId)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parking AreaId: " + parkingAreaId));

        return parkingAreaEntityToParkingAreaMapper.map(existingParkingArea);
    }

    /**
     * Retrieves a parking area by its name.
     *
     * @param name the name of the parking area to retrieve
     * @return the retrieved parking area
     */
    @Override
    public ParkingArea getParkingAreaByName(final String name) {
        final ParkingAreaEntity existingParkingArea = parkingAreaRepository.findByName(name)
                .orElseThrow(() -> new ParkingAreaNotFoundException("With given parking Area Name: " + name));

        return parkingAreaEntityToParkingAreaMapper.map(existingParkingArea);
    }

    /**
     * Retrieves daily income for a parking area on a given date.
     *
     * @param date         the date for which to retrieve income
     * @param parkingAreaId the ID of the parking area
     * @return the daily income of the parking area
     */
    @Override
    public ParkingAreaIncomeResponse getDailyIncome(final LocalDate date, final String parkingAreaId) {
        ParkingAreaEntity parkingAreaEntity = parkingAreaRepository
                .findById(parkingAreaId)
                .orElseThrow(ParkingAreaNotFoundException::new);

        isGivenDateAfterCurrentDate(date);

        BigDecimal calculatedIncome = parkingAreaRepository.calculateDailyIncome(date, parkingAreaId)
                .orElseThrow(DailyIncomeException::new);

        return ParkingAreaIncomeResponse.builder()
                .income(calculatedIncome)
                .name(parkingAreaEntity.getName())
                .build();
    }

    /**
     * Checks if the given date is after the current date.
     *
     * @param date the date to check
     * @throws InvalidDateException if the given date is after the current date
     */
    private void isGivenDateAfterCurrentDate(final LocalDate date) {
        if(Boolean.TRUE.equals(date.isAfter(LocalDate.now()))) {
            throw new InvalidDateException();
        }
    }
}
