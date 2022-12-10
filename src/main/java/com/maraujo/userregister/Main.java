package com.maraujo.userregister;

import com.maraujo.userregister.form.InputPayload;
import com.maraujo.userregister.form.chain.*;

public class Main {
    public static void main(String[] args) {
        ValidateName validateName = new ValidateName();
        ValidateBirthDate validateBirthDate = new ValidateBirthDate();
        ValidateCPF validateCPF = new ValidateCPF();
        ValidateAddress validateAddress = new ValidateAddress();
        ValidatePhone validatePhone = new ValidatePhone();

        InputPayload inputPayload = InputPayload.builder()
                .name("Jhon")
                .birthDate("06/22/2020")
                .cpf("7310903501")
                .street("Rua A")
                .streetNumber("42B")
                .city("Rio de Janeiro")
                .state("RJ")
                .postalCode("12345-001")
                .phone("(21) 9999-9999")
                .email("jhondoe@gmail.com")
                .build();

        Executor<InputPayload> executor = new Executor<>(inputPayload);

        executor
                .chain(validateName)
                .chain(validateBirthDate)
                .chain(validateCPF)
                .chain(validateAddress)
                .chain(validatePhone);

        System.out.println(inputPayload);
    }
}
