package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_BEFORE_AFTER;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_FORMAT;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateBirthDateTest {

    ValidateBirthDate validateBirthDate = new ValidateBirthDate();

    @Test
    void testExecute_nullBirthDate_throwsInvalidInputException() {
        RegisterPayload payload = new RegisterPayload();
        payload.setBirthDate(null);
        payload = validateBirthDate.execute(payload);
        assertTrue(payload.getErrors().containsKey("birthDate"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("birthDate"));
    }

    @Test
    void testExecute_blankBirthDate_throwsInvalidInputException() {
        RegisterPayload payload = new RegisterPayload();
        payload.setBirthDate("");
        payload = validateBirthDate.execute(payload);
        assertTrue(payload.getErrors().containsKey("birthDate"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("birthDate"));
    }

    @Test
    void testExecute_incorrectFormatBirthDate_throwsInvalidInputException() {
        RegisterPayload payload = new RegisterPayload();
        payload.setBirthDate("01-21-2000"); // incorrect format
        payload = validateBirthDate.execute(payload);
        assertTrue(payload.getErrors().containsKey("birthDate"));
        assertEquals(ERROR_MSG_BIRTH_DATE_FORMAT, payload.getErrors().get("birthDate"));
    }

    @Test
    void testExecute_BirthDateBeforeLimit_throwsInvalidInputException() {
        RegisterPayload payload = new RegisterPayload();
        payload.setBirthDate("01-01-1920");
        payload = validateBirthDate.execute(payload);
        assertTrue(payload.getErrors().containsKey("birthDate"));
        assertEquals(ERROR_MSG_BIRTH_DATE_BEFORE_AFTER, payload.getErrors().get("birthDate"));
    }

    @Test
    void testExecute_BirthDateAfterLimit_throwsInvalidInputException() {
        RegisterPayload payload = new RegisterPayload();
        payload.setBirthDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        payload = validateBirthDate.execute(payload);
        assertTrue(payload.getErrors().containsKey("birthDate"));
        assertEquals(ERROR_MSG_BIRTH_DATE_BEFORE_AFTER, payload.getErrors().get("birthDate"));
    }

    @Test
    void testExecute_validBirthDate(){
        RegisterPayload payload = new RegisterPayload();
        payload.setBirthDate("01-01-1922");
        payload = validateBirthDate.execute(payload);
        assertNull(payload.getErrors());
    }
}