package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.exception.InvalidInputException;
import com.maraujo.userregister.service.RegisterPayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_NAME_INVALID;
import static com.maraujo.userregister.service.Constants.StateProcess.FAILURE;
import static com.maraujo.userregister.service.Constants.StateProcess.PROCESSING;

@Log4j2
@Component
public class ValidateName implements ExecutorChain<RegisterPayload> {

    public static final String REGEX_FULL_NAME = "^[a-zA-ZJ]+(([',. -][a-zA-ZJ ])?[a-zA-ZJ]*)*$";
    public static final String NAME = "name";

    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        log.info("M execute, payload={}, state={}", payload, PROCESSING);
        try {
            inputValidate(payload.getName());
        } catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    static void inputValidate(String name){
        if (Objects.isNull(name)) throw new InvalidInputException(NAME, ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (name.isBlank()) throw new InvalidInputException(NAME, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (!name.trim().contains(" ")) throw new InvalidInputException(NAME, ERROR_MSG_NAME_INVALID);
        if (!name.matches(REGEX_FULL_NAME)) throw new InvalidInputException(NAME, ERROR_MSG_NAME_INVALID);
    }


}
