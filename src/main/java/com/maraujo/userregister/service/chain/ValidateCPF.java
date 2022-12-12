package com.maraujo.userregister.service.chain;

import com.maraujo.userregister.service.RegisterPayload;
import com.maraujo.userregister.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Objects;

import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static com.maraujo.userregister.service.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
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
        if (Objects.isNull(cpf)) throw new InvalidInputException("cpf", ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (cpf.isBlank()) throw new InvalidInputException("cpf", ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (!isValidCPF(cpf)) throw new  InvalidInputException("cpf", ERROR_MSG_CPF_INVALID);
    }

    private static boolean isValidCPF(String CPF) {
        // Removing any non-digit character from the CPF
        CPF = CPF.replaceAll("\\D", "");

        // Checking if the length of the CPF is 11
        if (CPF.length() != 11) {
            return false;
        }

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999"))
            return(false);


        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

}
