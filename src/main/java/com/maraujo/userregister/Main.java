package com.maraujo.userregister;

import com.maraujo.userregister.form.InputPayload;
import com.maraujo.userregister.form.chain.Executor;
import com.maraujo.userregister.form.chain.ValidateBirthDate;
import com.maraujo.userregister.form.chain.ValidateName;

public class Main {
    public static void main(String[] args) {
        ValidateName validateName = new ValidateName();
        ValidateBirthDate validateBirthDate = new ValidateBirthDate();

        InputPayload inputPayload = InputPayload.builder()
                .name("Jhon")
                .birthDate(null)
                .cpf("73109035014")
                .street("Rua A")
                .number("42B")
                .city("Rio de Janeiro")
                .state("RJ")
                .postalCode("12345-001")
                .phone("(21) 999999-9999")
                .email("jhondoe@gmail.com")
                .build();

        Executor<InputPayload> executor = new Executor<>(inputPayload);

        executor
                .chain(validateName)
                .chain(validateBirthDate);

        System.out.println(inputPayload);
    }
}
