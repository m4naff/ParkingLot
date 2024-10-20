package com.parkinglot.exception.parkingarea;

import com.parkinglot.exception.NotFoundException;

import java.io.Serial;

/**
 * Exception class named {@link ParkingAreaNotFoundException} thrown when a parking area is not found.
 */
public class ParkingAreaNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -75325461433448105L;

    private static final String DEFAULT_MESSAGE = "The specified ParkingAreaEntity does not exist";

    private static final String MESSAGE_TEMPLATE = "No ParkingAreaEntity found with given ID: ";

    public ParkingAreaNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ParkingAreaNotFoundException(String parkingAreaId) {
        super(MESSAGE_TEMPLATE.concat(parkingAreaId));
    }

}
