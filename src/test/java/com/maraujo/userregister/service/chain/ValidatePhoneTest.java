package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatePhoneTest {

    ValidatePhone validatePhone = new ValidatePhone();

    @Test
    void testExecute_validPhone(){
        var payload = RegisterPayload.builder().phone("(21) 9999-9999").build();
        payload = validatePhone.execute(payload);
        assertNull(payload.getErrors());
    }

}