package com.maraujo.userregister.model;

import com.maraujo.userregister.form.UserRegisterForm;

import java.time.LocalDate;

class UserRegisterFormTest {



    private static UserRegisterForm buildUser(){
        return new UserRegisterForm("Jhon Doe", LocalDate.of(1992,6,1),"73109035014",
                "Rua A", "42B", "Rio de Janeiro","RJ",  "12345-001",
                "(21) 999999-9999", "jhondoe@gmail.com");
    }
}
