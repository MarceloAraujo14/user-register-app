package com.maraujo.userregister.form;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
public class InputPayload {

    private String name;
    private String birthDate;
    private String cpf;
    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String phone;
    private String email;

    private Map<String, String> errors;

    public void buildError(String error, String message){
        if (Objects.isNull(errors)){
            this.errors = new HashMap<>();
            this.errors.put(error, message);
        }else {
            this.errors.put(error, message);
        }
    }

}
