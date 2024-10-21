package com.parkinglot.exception.parkingarea;

import java.io.Serial;

/**
 * Exception class named {@link InvalidDateException} thrown when an invalid date is provided.
 */
public class InvalidDateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3573563192487343800L;

    private static final String DEFAULT_MESSAGE =
            "Cannot be processed for a future date";

    public InvalidDateException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidDateException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
