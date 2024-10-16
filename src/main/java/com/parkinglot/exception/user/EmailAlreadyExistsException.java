package com.parkinglot.exception.user;

import java.io.Serial;

/**
 * Exception class named {@link EmailAlreadyExistsException} thrown when an email already exists in the system.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -435147781709055915L;

    private static final String DEFAULT_MESSAGE = "The specified email already exists";

    private static final String MESSAGE_TEMPLATE = "The email already exists: ";

    public EmailAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailAlreadyExistsException(String email) {
        super(MESSAGE_TEMPLATE.concat(email));
    }
}
