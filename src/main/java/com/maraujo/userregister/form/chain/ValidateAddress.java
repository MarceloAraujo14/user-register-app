package com.maraujo.userregister.form.chain;

import com.maraujo.userregister.form.InputPayload;
import com.maraujo.userregister.form.exception.InvalidInputException;

import java.util.Objects;

public class ValidateAddress implements ExecutorChain<InputPayload> {

    @Override
    public InputPayload execute(InputPayload payload) {
        executeAddressValidate(payload);
        return payload;
    }

    private static void executeAddressValidate(InputPayload payload){
        Executor<InputPayload> executorAddress = new Executor<>(payload);
        executorAddress
                .chain(new AddressValidate())
                .chain(new PostalCodeValidate());
    }

    static class AddressValidate implements ExecutorChain<InputPayload> {

        @Override
        public InputPayload execute(InputPayload payload) {
            try {
                inputValidate(payload.getStreet(), "street");
            }catch (InvalidInputException ex){
                payload.buildError(ex.getError(), ex.getMessage());
            }
            try {
                inputValidate(payload.getStreetNumber(), "streetNumber");
            }catch (InvalidInputException ex){
                payload.buildError(ex.getError(), ex.getMessage());
            }
            try {
                inputValidate(payload.getCity(), "city");
            }catch (InvalidInputException ex){
                payload.buildError(ex.getError(), ex.getMessage());
            }
            try {
                inputValidate(payload.getState(), "state");
            }catch (InvalidInputException ex){
                payload.buildError(ex.getError(), ex.getMessage());
            }
            return payload;
        }

        private static void inputValidate(String value, String field){
            if (Objects.isNull(value)) throw new InvalidInputException(field, "Insert a valid " + field);
            if (value.isBlank()) throw new InvalidInputException(field, "Insert a valid " + field);
        }
    }

    static class PostalCodeValidate implements ExecutorChain<InputPayload>{

        @Override
        public InputPayload execute(InputPayload payload) {
            try {
                inputValidate(payload.getPostalCode());
            }catch (InvalidInputException ex){
                payload.buildError(ex.getError(), ex.getMessage());
            }
            return payload;
        }

        private static void inputValidate(String postalCode){
            if (Objects.isNull(postalCode)) throw new InvalidInputException("postalCode","Field postalCode cannot be null");
            if (postalCode.isBlank()) throw new InvalidInputException("postalCode", "Field postalCode cannot be empty");
            if(!isValidPostalCode(postalCode)) throw new InvalidInputException("postalCode", "Insert a valid postalCode");
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
