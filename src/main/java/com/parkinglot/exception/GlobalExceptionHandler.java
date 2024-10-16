package com.parkinglot.exception;

import com.parkinglot.exception.user.EmailAlreadyExistsException;
import com.parkinglot.payload.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler class named {@link GlobalExceptionHandler} for handling various exceptions across the application.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles HTTP media type not supported exception.
     *
     * @param ex      The HttpMediaTypeNotSupportedException thrown
     * @param headers The headers of the HTTP request
     * @param status  The status of the HTTP request
     * @param request The HTTP request
     * @return ResponseEntity with an error response
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status,
                                                                     WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append("Unsupported media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Invalid JSON")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles HTTP message not readable exception.
     *
     * @param ex      The HttpMessageNotReadableException thrown
     * @param headers The headers of the HTTP request
     * @param status  The status of the HTTP request
     * @param request The HTTP request
     * @return ResponseEntity with an error response
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Malformed JSON request")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles method argument not valid exception.
     *
     * @param ex      The MethodArgumentNotValidException thrown
     * @param headers The headers of the HTTP request
     * @param status  The status of the HTTP request
     * @param request The HTTP request
     * @return ResponseEntity with an error response
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Validation Errors")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles missing servlet request parameter exception.
     *
     * @param ex      The MissingServletRequestParameterException thrown
     * @param headers The headers of the HTTP request
     * @param status  The status of the HTTP request
     * @param request The HTTP request
     * @return ResponseEntity with an error response
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();
        details.add(ex.getParameterName() + " parameter is missing");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Validation Errors")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    /**
     * Handles email already exists exception.
     *
     * @param ex The EmailAlreadyExistsException thrown
     * @return ResponseEntity with an error response
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(final EmailAlreadyExistsException ex) {
        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Email Already Exists")
                .statusCode(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}
