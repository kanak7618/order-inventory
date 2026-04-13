package com.spring.order_inventory.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleIdNotFoundException(
            IdNotFoundException e, HttpServletRequest request) {

        ExceptionDto dto = new ExceptionDto(
                404, "NOT_FOUND", e.getMessage(), request.getRequestURI()
        );

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ExceptionDto> handleInvalidFormatException(
            InvalidFormatException e, HttpServletRequest request) {

        ExceptionDto dto = new ExceptionDto(
                400, "BAD_REQUEST", e.getMessage(), request.getRequestURI()
        );

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleResourceNotFoundException(
            ResourceNotFoundException e, HttpServletRequest request) {

        ExceptionDto dto = new ExceptionDto(
                404, "NOT_FOUND", e.getMessage(), request.getRequestURI()
        );

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}