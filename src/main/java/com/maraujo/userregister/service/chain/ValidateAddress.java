package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_INVALID_POSTALCODE;

@Component
public class ValidateAddress implements ExecutorChain<RegisterPayload> {

    public static final String POSTAL_CODE = "postalCode";


    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        executeAddressValidate(payload);
        return payload;
    }

    private static void executeAddressValidate(RegisterPayload payload){
        Executor<RegisterPayload> executorAddress = new Executor<>(payload);
        executorAddress
                .chain(new AddressValidate())
                .chain(new PostalCodeValidate());
    }

    static class AddressValidate implements ExecutorChain<RegisterPayload> {

        @Override
        public RegisterPayload execute(RegisterPayload payload) {
            try {
                inputValidate(payload.getStreet(), "street");
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
            }
            try {
                inputValidate(payload.getStreetNumber(), "streetNumber");
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
            }
            try {
                inputValidate(payload.getCity(), "city");
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
            }
            try {
                inputValidate(payload.getState(), "state");
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
            }
            return payload;
        }

        private static void inputValidate(String value, String field){
            if (Objects.isNull(value)) throw new InvalidInputException(field, ERROR_MSG_CANNOT_BE_NULL);
            if (value.isBlank()) throw new InvalidInputException(field, ERROR_MSG_CANNOT_BE_EMPTY);
        }
    }

    static class PostalCodeValidate implements ExecutorChain<RegisterPayload>{

        @Override
        public RegisterPayload execute(RegisterPayload payload) {
            try {
                inputValidate(payload.getPostalCode());
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
            }
            return payload;
        }

        private static void inputValidate(String postalCode){
            if (Objects.isNull(postalCode)) throw new InvalidInputException(POSTAL_CODE,ERROR_MSG_CANNOT_BE_NULL);
            if (postalCode.isBlank()) throw new InvalidInputException(POSTAL_CODE, ERROR_MSG_CANNOT_BE_EMPTY);
            if(!isValidPostalCode(postalCode)) throw new InvalidInputException(POSTAL_CODE, ERROR_MSG_INVALID_POSTALCODE);
        }

        private static boolean isValidPostalCode(String postalCode){
            if (postalCode.matches("\\w")){
                return false;
            }
            postalCode = postalCode.replaceAll("\\D", "");
            return postalCode.length() == 8;
        }
    }

}
