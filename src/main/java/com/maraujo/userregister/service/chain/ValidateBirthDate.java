package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_BEFORE_AFTER;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_FORMAT;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;

@Component
public class ValidateBirthDate implements ExecutorChain<RegisterPayload>{

    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 100;
    public static final String BIRTH_DATE = "birthDate";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";

    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        try {
            inputValidate(payload.getBirthDate());
        } catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
        }
        return payload;
    }

    private static void inputValidate(String birthDate){
        if (Objects.isNull(birthDate)) throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (birthDate.isBlank()) throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        LocalDate date;
        try {
            birthDate = birthDate.replace("/", "-");
          date = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern(DD_MM_YYYY));
        } catch (Exception exception){
            throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_BIRTH_DATE_FORMAT);
        }
        if (!isValid(date)) throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_BIRTH_DATE_BEFORE_AFTER);

    }

    private static boolean isValid(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        if (birthDate.isAfter(today)) return false;
        Period age = Period.between(birthDate, today);
        return age.getYears() > MIN_AGE && age.getYears() <= MAX_AGE;
    }
}
