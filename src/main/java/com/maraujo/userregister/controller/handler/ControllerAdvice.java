package com.maraujo.userregister.controller.handler;

import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public Map<String, String> inputExceptionHandler(InvalidInputException ex){
        return ex.getErrorDetails();
    }

}
