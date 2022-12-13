package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import org.junit.jupiter.api.Test;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_EMAIL_INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateEmailTest {
    ValidateEmail validateEmail = new ValidateEmail();

    @Test
    void testExecute_validEmail(){
        var payload = RegisterPayload.builder().email("jhondoe@gmail.com").build();
        payload = validateEmail.execute(payload);

        assertNull(payload.getErrors());
    }

    @Test
    void shouldThrow_invalidEmail_withoutArroba(){
        var payload = RegisterPayload.builder().email("jhondoegmail.com").build();
        payload = validateEmail.execute(payload);

        assertTrue(payload.getErrors().containsKey("email"));
        assertEquals(ERROR_MSG_EMAIL_INVALID, payload.getErrors().get("email"));
    }

    @Test
    void shouldThrow_invalidEmail_withoutDomain(){
        var payload = RegisterPayload.builder().email("jhondoe@.com").build();
        payload = validateEmail.execute(payload);

        assertTrue(payload.getErrors().containsKey("email"));
        assertEquals(ERROR_MSG_EMAIL_INVALID, payload.getErrors().get("email"));
    }

    @Test
    void shouldThrow_invalidEmail_withoutTopLevelDomain(){
        var payload = RegisterPayload.builder().email("jhondoe@gmail").build();
        payload = validateEmail.execute(payload);

        assertTrue(payload.getErrors().containsKey("email"));
        assertEquals(ERROR_MSG_EMAIL_INVALID, payload.getErrors().get("email"));
    }

    @Test
    void shouldThrow_invalidEmail_Null(){
        var payload = RegisterPayload.builder().build();
        payload = validateEmail.execute(payload);

        assertTrue(payload.getErrors().containsKey("email"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("email"));
    }

    @Test
    void shouldThrow_invalidEmail_Empty(){
        var payload = RegisterPayload.builder().email("").build();
        payload = validateEmail.execute(payload);

        assertTrue(payload.getErrors().containsKey("email"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("email"));
    }


}