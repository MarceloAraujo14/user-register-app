package com.maraujo.userregister.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterPayloadTest {

    @Test
    void testBuilder_validValues_createsValidPayload() {
        RegisterPayload payload = RegisterPayload.builder()
                .name("John Doe")
                .birthDate("01/01/1970")
                .cpf("123.456.789-00")
                .street("Main St.")
                .streetNumber("123")
                .city("Boston")
                .state("MA")
                .postalCode("12345")
                .phone("(123) 456-7890")
                .email("johndoe@example.com")
                .build();

        assertEquals("John Doe", payload.getName(), "builder should set the name correctly");
        assertEquals("01/01/1970", payload.getBirthDate(), "builder should set the birthDate correctly");
        assertEquals("123.456.789-00", payload.getCpf(), "builder should set the cpf correctly");
        assertEquals("Main St.", payload.getStreet(), "builder should set the street correctly");
        assertEquals("123", payload.getStreetNumber(), "builder should set the streetNumber correctly");
        assertEquals("Boston", payload.getCity(), "builder should set the city correctly");
        assertEquals("MA", payload.getState(), "builder should set the state correctly");
        assertEquals("12345", payload.getPostalCode(), "builder should set the postalCode correctly");
        assertEquals("(123) 456-7890", payload.getPhone(), "builder should set the phone correctly");
        assertEquals("johndoe@example.com", payload.getEmail(), "builder should set the email correctly");
    }

}
