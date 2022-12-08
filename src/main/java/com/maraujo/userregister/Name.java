package com.maraujo.userregister;

import lombok.Getter;
import lombok.Setter;

import static com.maraujo.userregister.Constants.ErrorMessage.*;

@Getter @Setter
public class Name {

    private static final String FULL_NAME_REGEX = "^[a-zA-ZJ]+(([',. -][a-zA-ZJ ])?[a-zA-ZJ]*)*$";

    public static final String FULL_NAME = "Full Name";

    private String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String fullName) {
        if (fullName == null) throw new InvalidFieldException(FULL_NAME, ERROR_MSG_NAME_CANNOT_BE_NULL);
        if (fullName.isBlank()) throw  new InvalidFieldException(FULL_NAME, ERROR_MSG_NAME_CANNOT_BE_EMPTY);
        if (!fullName.matches(FULL_NAME_REGEX)) throw new InvalidFieldException(FULL_NAME, ERROR_MSG_NAME_NOT_VALID);
        if (!fullName.trim().contains(" ")) throw new InvalidFieldException(FULL_NAME, ERROR_MSG_NAME_NOT_VALID);
    }

}
