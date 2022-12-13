package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_EMAIL_INVALID;
import static com.maraujo.userregister.service.Constants.StateProcess.FAILURE;
import static com.maraujo.userregister.service.Constants.StateProcess.PROCESSING;

@Log4j2
@Component
public class ValidateEmail implements ExecutorChain<RegisterPayload> {

    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String EMAIL = "email";


    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        log.info("M execute, payload={}, state={}", payload, PROCESSING);
        try {
            inputValidate(payload.getEmail());
        } catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String email){
        if (Objects.isNull(email)) throw new InvalidInputException(EMAIL, ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (email.isBlank()) throw new InvalidInputException(EMAIL, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (email.trim().contains(" ") || !email.matches(EMAIL_REGEX)) throw new InvalidInputException(EMAIL, ERROR_MSG_EMAIL_INVALID);
    }

}
