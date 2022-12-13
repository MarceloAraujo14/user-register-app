package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_NAME_INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateNameTest {

    private final ValidateName validateName = new ValidateName();

    @Test
    void testExecute_nullName_setsErrorOnPayload() {
        RegisterPayload payload = RegisterPayload.builder().build();
        payload = validateName.execute(payload);
        assertTrue(payload.getErrors().containsKey("name"),
                "execute should set an error on the payload when the name is null");
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("name"),
                "execute should set the correct error message when the name is null");
    }

    @Test
    void testExecute_emptyName_setsErrorOnPayload() {
        RegisterPayload payload = RegisterPayload.builder().name("").build();
        payload = validateName.execute(payload);
        assertTrue(payload.getErrors().containsKey("name"),
                "execute should set an error on the payload when the name is an empty string");
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("name"),
                "execute should set the correct error message when the name is an empty string");
    }

    @Test
    void testExecute_singleWordName_setsErrorOnPayload() {
        RegisterPayload payload = RegisterPayload.builder().name("John").build();
        payload = validateName.execute(payload);
        assertTrue(payload.getErrors().containsKey("name"),
                "execute should set an error on the payload when the name is a single word");
        assertEquals(ERROR_MSG_NAME_INVALID, payload.getErrors().get("name"),
                "execute should set the correct error message when the name is a single word");
    }

    @Test
    void testExecute_invalidCharacters_setsErrorOnPayload() {
        RegisterPayload payload = RegisterPayload.builder().name("John Doe!").build();
        payload = validateName.execute(payload);
        assertTrue(payload.getErrors().containsKey("name"),
                "execute should set an error on the payload when the name contains invalid characters");
        assertEquals(ERROR_MSG_NAME_INVALID, payload.getErrors().get("name"),
                "execute should set the correct error message when the name contains invalid characters");
    }

    @Test
    void testExecute_validName_doesNotSetErrorOnPayload() {
        RegisterPayload payload = RegisterPayload.builder().name("John Doe").build();
        payload = validateName.execute(payload);
        assertTrue(Objects.isNull(payload.getErrors()),
                "execute should not set an error on the payload when the name is valid");
    }
}

