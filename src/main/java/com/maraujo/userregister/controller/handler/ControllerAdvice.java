package com.maraujo.userregister.controller.handler;

import com.maraujo.userregister.exception.InvalidInputException;
import com.maraujo.userregister.exception.RegisterAlreadyExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public Map<String, String> inputExceptionHandler(InvalidInputException ex){
        return ex.getErrorDetails();
    }

    @ExceptionHandler(RegisterAlreadyExistsException.class)
    public Map<String, String> inputExceptionHandler(RegisterAlreadyExistsException ex){
        return Map.of(ex.getError(), ex.getMessage());
    }

}
