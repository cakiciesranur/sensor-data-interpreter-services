package com.eny.sensordatainterpreter.exception;

import com.eny.sensordatainterpreter.exception.customexceptions.ApplicationRuntimeException;
import com.eny.sensordatainterpreter.exception.customexceptions.InvalidDateFormatException;
import com.eny.sensordatainterpreter.exception.customexceptions.InvalidDateIntervalException;
import com.eny.sensordatainterpreter.exception.customexceptions.NotFoundException;
import com.eny.sensordatainterpreter.payload.response.GenericErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<GenericErrorResponse> handleNotFoundException(ApplicationRuntimeException e) {
        return new ResponseEntity<>(new GenericErrorResponse(e.getMessage(), e.getErrorCode()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidDateFormatException.class, InvalidDateIntervalException.class})
    public final ResponseEntity<GenericErrorResponse> invalidDateFormatException(ApplicationRuntimeException e) {
        return new ResponseEntity<>(new GenericErrorResponse(e.getMessage(), e.getErrorCode()),
                HttpStatus.BAD_REQUEST);
    }
}
