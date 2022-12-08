package com.maraujo.userregister.model;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
public class BirthDate {

    LocalDate value;

    public BirthDate(LocalDate value) {
        inputValidate(value);
        this.value = value;
    }

    private static void inputValidate(LocalDate birthDate){
        if (Objects.isNull(birthDate)) throw new IllegalArgumentException();
        try {
           birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception exception){
            throw new IllegalArgumentException();
        }
        if (!isValid(birthDate)) throw new IllegalArgumentException();
    }

    private static boolean isValid(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        if (birthDate.isAfter(today)) return false;
        Period age = Period.between(birthDate, today);
        return age.getYears() > 1 && age.getYears() <= 100;
    }

}
