package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CANNOT_BE_NULL;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_CPF_INVALID;

@Component
public class ValidateCPF implements ExecutorChain<RegisterPayload> {

    @Override
    public RegisterPayload execute(RegisterPayload payload) {
        try {
            inputValidate(payload.getCpf());
        } catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
        }
        return payload;
    }

    private static void inputValidate(String cpf){
        if (Objects.isNull(cpf)) throw new InvalidInputException("cpf", ERROR_MSG_CANNOT_BE_NULL);
        if (cpf.isBlank()) throw new InvalidInputException("cpf", ERROR_MSG_CANNOT_BE_EMPTY);
        if (!isValidCPF(cpf)) throw new  InvalidInputException("cpf", ERROR_MSG_CPF_INVALID);
    }

    public static boolean isValidCPF(String cpf) {
        // Removing any non-digit character from the CPF
        cpf = cpf.replaceAll("\\D", "");

        // Checking if the length of the CPF is 11
        if (cpf.length() != 11) {
            return false;
        }

        // Initializing the first and second sum of the verification digit calculation
        int sum1 = 0;
        int sum2 = 0;

        // Validating the first nine digits
        for (int i = 0; i < 9; i++) {
            sum1 += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
            sum2 += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
        }

        // Calculating the first and second verification digits
        int digit1 = 11 - (sum1 % 11);
        if (digit1 > 9) {
            digit1 = 0;
        }
        int digit2 = 11 - (sum2 % 11);
        if (digit2 > 9) {
            digit2 = 0;
        }

        // Checking if the calculated digits match the last two digits of the CPF
        return cpf.substring(9, 11).equals(digit1 + "" + digit2);
    }

}
