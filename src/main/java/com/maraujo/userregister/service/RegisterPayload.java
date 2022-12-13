package com.maraujo.userregister.service;

import com.maraujo.userregister.repository.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPayload {

    private String name;
    private String birthDate;
    private String cpf;
    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String postalCode;
    private String phone;
    private String email;

    private Map<String, String> errors;

    public void putError(String error, String message){
        if (Objects.isNull(errors)){
            this.errors = new HashMap<>();
            this.errors.put(error, message);
        }else {
            this.errors.put(error, message);
        }
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .name(this.name)
                .cpf(this.cpf)
                .birthDate(LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .street(this.street)
                .streetNumber(this.streetNumber)
                .city(this.city)
                .state(this.state)
                .postalCode(this.postalCode)
                .phone(this.phone)
                .email(this.email)
                .build();
    }

}
