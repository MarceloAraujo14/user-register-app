package com.maraujo.userregister.form.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InvalidInputException extends RuntimeException{

    private String error;
    private String message;

    private Map<String, String> errorDetails;

    public InvalidInputException() {
    }

    public InvalidInputException(String error, String message) {
        this.error = error;
        this.message = message;
        this.errorDetails = Map.of(error, message);
    }
}
