package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_NAME_NOT_VALID;

@Component
public class ValidateName implements ExecutorChain<RegisterPayload> {

    public static final String REGEX_FULL_NAME = "^[a-zA-ZJ]+(([',. -][a-zA-ZJ ])?[a-zA-ZJ]*)*$";
    public static final String NAME = "name";

    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        try {
            inputValidate(payload.getName());
        } catch (InvalidInputException ex){
            payload.buildError(ex.getError(), ex.getMessage());
        }
        return payload;
    }

    private static void inputValidate(String name){
        if (Objects.isNull(name)) throw new InvalidInputException(NAME, ERROR_MSG_CANNOT_BE_NULL);
        if (name.isBlank()) throw new InvalidInputException(NAME, ERROR_MSG_CANNOT_BE_EMPTY);
        if (!name.trim().contains(" ")) throw new InvalidInputException(NAME, ERROR_MSG_NAME_NOT_VALID);
        if (!name.matches(REGEX_FULL_NAME)) throw new InvalidInputException(NAME, ERROR_MSG_NAME_NOT_VALID);
    }


}
