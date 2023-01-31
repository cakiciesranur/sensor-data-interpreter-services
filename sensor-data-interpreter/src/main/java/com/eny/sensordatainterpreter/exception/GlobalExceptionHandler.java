package com.eny.sensordatainterpreter.exception;

import com.eny.sensordatainterpreter.enums.ErrorMessage;
import com.eny.sensordatainterpreter.exception.generalruntime.ApplicationRuntimeException;
import com.eny.sensordatainterpreter.payload.response.GenericResponse;
import com.eny.sensordatainterpreter.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    GenericResponseService genericResponseService;

    @ExceptionHandler({ApplicationRuntimeException.class})
    public final ResponseEntity<GenericResponse<Object>> handleApplicationRuntimeException(ApplicationRuntimeException e) {
        return new ResponseEntity<GenericResponse<Object>>(genericResponseService.createResponseWithError(e.getErrorCode()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<GenericResponse<Object>> handleApplicationRuntimeException(MethodArgumentNotValidException e) {
        return new ResponseEntity<GenericResponse<Object>>(genericResponseService.createResponseWithError(ErrorMessage.VALIDATION_ERROR.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<GenericResponse<Object>> handleOtherException(Exception e) {
        return new ResponseEntity<GenericResponse<Object>>(genericResponseService.createResponseWithError(ErrorMessage.UNKNOWN_ERROR.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
