package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_INVALID_EMAIL;


@Component
public class ValidateEmail implements ExecutorChain<RegisterPayload> {

    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String EMAIL = "email";


    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        try {
            inputValidate(payload.getEmail());
        } catch (InvalidInputException ex){
            payload.buildError(ex.getError(), ex.getMessage());
        }
        return payload;
    }

    private static void inputValidate(String email){
        if (Objects.isNull(email)) throw new InvalidInputException(EMAIL, ERROR_MSG_CANNOT_BE_NULL);
        if (email.isBlank()) throw new InvalidInputException(EMAIL, ERROR_MSG_CANNOT_BE_EMPTY);
        if (!email.trim().contains(" ") || !email.matches(EMAIL_REGEX)) throw new InvalidInputException(EMAIL, ERROR_MSG_INVALID_EMAIL);
    }

}
