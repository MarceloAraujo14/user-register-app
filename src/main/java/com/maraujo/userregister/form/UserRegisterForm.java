package com.maraujo.userregister.form;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

//salvar, recuperar, alterar, deletar
//dados: Nome, Data de Nascimento, CPF, Endereço contendo (Logradouro, Número, Cidade, Estado, CEP), Telefone e Email.

@Getter @ToString
public class UserRegisterForm {

    private String name;
    private LocalDate birthDate;
    private String cpf;
    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String phone;
    private String email;

    public UserRegisterForm(String name, LocalDate birthDate, String cpf, String street, String number, String city, String state,
                            String postalCode, String phone, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        validate();
    }

    private void validate(){
    }

    private InputPayload buildPayload(){
        return InputPayload.builder()
                .name(this.name)
                .build();
    }

}
