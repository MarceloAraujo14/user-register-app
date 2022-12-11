package com.maraujo.userregister;

import com.maraujo.userregister.service.RegisterPayload;

public class RegisterPayloadBuild {

    public static RegisterPayload build(){
        return RegisterPayload.builder()
                .name("John Doe")
                .birthDate("01/01/1970")
                .cpf("123.456.789-00")
                .street("Main St.")
                .streetNumber("123")
                .city("Boston")
                .state("MA")
                .postalCode("12345-000")
                .phone("(123) 456-7890")
                .email("johndoe@example.com")
                .build();
    }

}
