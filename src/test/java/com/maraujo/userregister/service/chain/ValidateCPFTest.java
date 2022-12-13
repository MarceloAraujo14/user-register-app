package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import org.junit.jupiter.api.Test;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CPF_INVALID;
import static org.junit.jupiter.api.Assertions.*;

class ValidateCPFTest {

    ValidateCPF validateCPF = new ValidateCPF();

    @Test
    void testExecute_validCPF() {
        RegisterPayload payload = RegisterPayload.builder().cpf("728.677.030-69").build();
        payload = validateCPF.execute(payload);
        assertNull(payload.getErrors());
    }

    @Test
    void testExecute_validCPF_onlyDigits() {
        RegisterPayload payload = RegisterPayload.builder().cpf("53497167002").build();
        payload = validateCPF.execute(payload);
        assertNull(payload.getErrors());
    }

    @Test
    void testExecute_invalidCPF_IncorrectVerificationDigits() {
        RegisterPayload payload = RegisterPayload.builder().cpf("123.456.789-11").build();
        payload = validateCPF.execute(payload);

        assertTrue(payload.getErrors().containsKey("cpf"));
        assertEquals(ERROR_MSG_CPF_INVALID, payload.getErrors().get("cpf"));
    }

    @Test
    void testExecute_invalidCPF_ContainsNonDigitCharacters() {
        RegisterPayload payload = RegisterPayload.builder().cpf("123.456.789-ab").build();
        payload = validateCPF.execute(payload);

        assertTrue(payload.getErrors().containsKey("cpf"));
        assertEquals(ERROR_MSG_CPF_INVALID, payload.getErrors().get("cpf"));
    }

    @Test
    void testExecute_invalidCPF_tooShort() {
        RegisterPayload payload = RegisterPayload.builder().cpf("123456789").build();
        payload = validateCPF.execute(payload);

        assertTrue(payload.getErrors().containsKey("cpf"));
        assertEquals(ERROR_MSG_CPF_INVALID, payload.getErrors().get("cpf"));

//        assertFalse(isValidCPF("12345678901")); // Too long
    }

    @Test
    void testExecute_invalidCPF_tooLong() {
        RegisterPayload payload = RegisterPayload.builder().cpf("12345678901").build();
        payload = validateCPF.execute(payload);

        assertTrue(payload.getErrors().containsKey("cpf"));
        assertEquals(ERROR_MSG_CPF_INVALID, payload.getErrors().get("cpf"));

    }

    @Test
    void testExecute_invalidCPF_Null() {
        RegisterPayload payload = RegisterPayload.builder().build();
        payload = validateCPF.execute(payload);

        assertTrue(payload.getErrors().containsKey("cpf"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("cpf"));

    }

    @Test
    void testExecute_invalidCPF_Empty() {
        RegisterPayload payload = RegisterPayload.builder().cpf("").build();
        payload = validateCPF.execute(payload);

        assertTrue(payload.getErrors().containsKey("cpf"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("cpf"));

    }


}