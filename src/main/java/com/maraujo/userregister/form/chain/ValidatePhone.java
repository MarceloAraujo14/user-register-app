package com.maraujo.userregister.form.chain;

import com.maraujo.userregister.form.InputPayload;
import com.maraujo.userregister.form.exception.InvalidInputException;

import java.util.Objects;

import static com.maraujo.userregister.form.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.form.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_NULL;
import static com.maraujo.userregister.form.Constants.ErrorMessage.ERROR_MSG_INVALID_PHONENUMBER;

public class ValidatePhone implements ExecutorChain<InputPayload> {

    public static final String PHONE_NUMBER = "phoneNumber";


    @Override
    public InputPayload execute(InputPayload payload) {
        try {
            inputValidate(payload.getPhone());
        }catch (InvalidInputException ex){
            payload.buildError(ex.getError(), ex.getMessage());
        }
        return payload;
    }

    private static void inputValidate(String phone){
        if (Objects.isNull(phone)) throw new InvalidInputException(PHONE_NUMBER, ERROR_MSG_CANNOT_BE_NULL);
        if (phone.isBlank()) throw new InvalidInputException(PHONE_NUMBER, ERROR_MSG_CANNOT_BE_EMPTY);
        if(!isValidPhoneNumber(phone)) throw new InvalidInputException(PHONE_NUMBER, ERROR_MSG_INVALID_PHONENUMBER);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Removing any non-digit character from the phone number
        phoneNumber = phoneNumber.replaceAll("\\D", "");

        // Checking if the phone number has 11 or 10 digits
        if (phoneNumber.length() != 11 && phoneNumber.length() != 10) {
            return false;
        }

        // The first two digits of the phone number represent the DDD
        int ddd = Integer.parseInt(phoneNumber.substring(0, 2));

        // Validating the DDD
        if (ddd < 11 || ddd > 99) {
            return false;
        }

        // The remaining digits of the phone number represent the subscriber number
        String subscriberNumber = phoneNumber.substring(2);

        // Validating the subscriber number
        return subscriberNumber.matches("[1-9]\\d{4,5}");
    }

}
