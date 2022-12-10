package com.maraujo.userregister.model;

import com.maraujo.userregister.form.UserRegisterForm;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRegisterFormTest {

    //nome nao deveria ser nulo
    @Test
    void shouldThrowWhenNameIsNull(){
        //given
        UserRegisterForm form = buildUser();
        form.setName(null);
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserRegisterForm(form.getName(), form.getBirthDate(), form.getCpf(),
                form.getStreet(), form.getNumber(), form.getCity(), form.getState(), form.getPostalCode(), form.getPhone(), form.getEmail()));
    }

    //nome nao deveria estar vazio = "", "    "
    @Test
    void shouldThrowWhenNameIsEmpty(){
        //given
        UserRegisterForm form = buildUser();
        form.setName(" ");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserRegisterForm(form.getName(), form.getBirthDate(), form.getCpf(),
                form.getStreet(), form.getNumber(), form.getCity(), form.getState(), form.getPostalCode(), form.getPhone(), form.getEmail()));
    }

    //nome deveria ter nome e pelo menos um sobrenome
    @Test
    void shouldThrowWhenNameIsNotComplete(){
        //given
        UserRegisterForm form = buildUser();
        form.setName(" Jhon ");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserRegisterForm(form.getName(), form.getBirthDate(), form.getCpf(),
                form.getStreet(), form.getNumber(), form.getCity(), form.getState(), form.getPostalCode(), form.getPhone(), form.getEmail()));
    }

    //nome nao deveria ter numeros
    @Test
    void shouldThrowWhenNameHaveNumber(){
        //given
        UserRegisterForm form = buildUser();
        form.setName("Jhon123 Doe");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserRegisterForm(form.getName(), form.getBirthDate(), form.getCpf(),
                form.getStreet(), form.getNumber(), form.getCity(), form.getState(), form.getPostalCode(), form.getPhone(), form.getEmail()));
    }

    //nome nao deveria ter caracter especial
    @Test
    void shouldThrowWhenNameHaveSpecialCharacter(){
        //given
        UserRegisterForm form = buildUser();
        form.setName("Jhon Doe!");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserRegisterForm(form.getName(), form.getBirthDate(), form.getCpf(),
                form.getStreet(), form.getNumber(), form.getCity(), form.getState(), form.getPostalCode(), form.getPhone(), form.getEmail()));
    }

    //nome correto deveria nao lançar erro
    @Test
    void nameShouldBeValid(){
        assertDoesNotThrow(UserRegisterFormTest::buildUser);
    }

    private static UserRegisterForm buildUser(){
        return new UserRegisterForm("Jhon Doe", LocalDate.of(1992,6,1),"73109035014",
                "Rua A", "42B", "Rio de Janeiro","RJ",  "12345-001",
                "(21) 999999-9999", "jhondoe@gmail.com");
    }
}
