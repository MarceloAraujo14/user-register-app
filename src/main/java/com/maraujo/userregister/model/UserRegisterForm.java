package com.maraujo.userregister.model;

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

        this.name = new Name(name).getValue();
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = new Name(name).getValue();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        UserRegisterForm userRegisterForm = new UserRegisterForm("Jhon", LocalDate.now(), "000000", "", " ",
                "", "", "", "(21) 98999-9999", "jhondo");

        System.out.println(userRegisterForm.getName());
    }

}
