package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.RegisterPayloadBuild;
import com.maraujo.userregister.service.RegisterPayload;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_INVALID_POSTALCODE;
import static org.junit.jupiter.api.Assertions.*;

class ValidateAddressTest {

    ValidateAddress validateAddress = new ValidateAddress();

    @Test
    void shouldThrow_Street_Null(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setStreet(null);
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("street"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("street"));
    }

    @Test
    void shouldThrow_Street_Empty(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setStreet("");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("street"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("street"));
    }

    @Test
    void shouldThrow_StreetNumber_Null(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setStreetNumber(null);
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("streetNumber"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("streetNumber"));
    }

    @Test
    void shouldThrow_StreetNumber_Empty(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setStreetNumber("");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("streetNumber"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("streetNumber"));
    }

    @Test
    void shouldThrow_City_Null(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setCity(null);
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("city"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("city"));
    }

    @Test
    void shouldThrow_City_Empty(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setCity("");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("city"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("city"));
    }

    @Test
    void shouldThrow_State_Null(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setState(null);
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("state"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("state"));
    }

    @Test
    void shouldThrow_State_Empty(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setState("");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("state"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("state"));
    }

    @Test
    void shouldThrow_PostalCode_Null(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setPostalCode(null);
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("postalCode"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_NULL, payload.getErrors().get("postalCode"));
    }

    @Test
    void shouldThrow_PostalCode_Empty(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setPostalCode("");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("postalCode"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, payload.getErrors().get("postalCode"));
    }

    @Test
    void shouldThrow_PostalCode_Invalid_with_letter(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setPostalCode("00500-abc");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("postalCode"));
        assertEquals(ERROR_MSG_INVALID_POSTALCODE, payload.getErrors().get("postalCode"));
    }

    @Test
    void shouldThrow_PostalCode_Invalid_not_eight_digits(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload.setPostalCode("00500-00");
        payload = validateAddress.execute(payload);

        assertTrue(payload.getErrors().containsKey("postalCode"));
        assertEquals(ERROR_MSG_INVALID_POSTALCODE, payload.getErrors().get("postalCode"));
    }

    @Test
    void assertValidAddress(){
        RegisterPayload payload = RegisterPayloadBuild.build();
        payload = validateAddress.execute(payload);

        assertTrue(Objects.isNull(payload.getErrors()));
    }

}