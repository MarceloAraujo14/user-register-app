package com.maraujo.userregister;

import java.util.Map;

public class InvalidFieldException extends RuntimeException {

    private Map<String, String> errorDetails;

    public InvalidFieldException(String error, String message){
        this.errorDetails = Map.of(error, message);
    }

}
