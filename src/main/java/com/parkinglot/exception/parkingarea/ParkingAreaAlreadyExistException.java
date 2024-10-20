package com.parkinglot.exception.parkingarea;

import com.parkinglot.exception.AlreadyException;

import java.io.Serial;

public class ParkingAreaAlreadyExistException extends AlreadyException {

    @Serial
    private static final long serialVersionUID = -4515385870402222793L;

    private static final String DEFAULT_MESSAGE = "The Parking Area Name and Location Already exist!";

    public ParkingAreaAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

     public ParkingAreaAlreadyExistException(String message) {
        super(DEFAULT_MESSAGE + " " + message);
     }
}
