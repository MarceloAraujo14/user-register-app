package com.maraujo.userregister;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static com.maraujo.userregister.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_BEFORE_AFTER;
import static com.maraujo.userregister.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_FORMAT;
import static com.maraujo.userregister.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_EMPTY;
import static com.maraujo.userregister.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_NULL;

@Getter @Setter
public class BirthDate {

    public static final String DATE_OF_BIRTH = "Date of Birth";

    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 100;

    private LocalDate value;

    public BirthDate(String birthDate) {
        this.value = validate(birthDate);
    }

    private static LocalDate validate(String dateOfBirth) {
        if (dateOfBirth == null) throw new InvalidFieldException(DATE_OF_BIRTH, ERROR_MSG_BIRTH_DATE_NULL);
        if (dateOfBirth.isBlank()) throw new InvalidFieldException(DATE_OF_BIRTH, ERROR_MSG_BIRTH_DATE_EMPTY);
        LocalDate toDate;
        try {
           toDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e){
            throw new InvalidFieldException(DATE_OF_BIRTH, ERROR_MSG_BIRTH_DATE_FORMAT);
        }
        if (!isValid(toDate)) throw new InvalidFieldException(DATE_OF_BIRTH, ERROR_MSG_BIRTH_DATE_BEFORE_AFTER);

        return toDate;
    }

    private static boolean isValid(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();
        if (dateOfBirth.isAfter(today)) return false;
        Period age = Period.between(dateOfBirth, today);
        return age.getYears() >= MIN_AGE && age.getYears() < MAX_AGE;
    }

}
