package com.maraujo.userregister.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

    //nome nao deveria ser nulo
    @Test
    void shouldThrowWhenNameIsNull(){
        //given
        String name = null;
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }

    //nome nao deveria estar vazio = "", "    "
    @Test
    void shouldThrowWhenNameIsEmpty(){
        //given
        String name = " ";
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }

    //nome deveria ter nome e pelo menos um sobrenome
    @Test
    void shouldThrowWhenNameIsNotComplete(){
        //given
        String name = " Jhon ";
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }

    //nome nao deveria ter numeros
    @Test
    void shouldThrowWhenNameHaveNumber(){
        //given
       String name = "Jhon123 Doe";
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }

    //nome nao deveria ter caracter especial
    @Test
    void shouldThrowWhenNameHaveSpecialCharacter(){
        //given
        String name = "Jhon Doe!";
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }

    //nome correto deveria nao lançar erro
    @Test
    void nameShouldBeValid(){

        assertDoesNotThrow(() -> {
            String name = "Jhon Doe";
            return new Name(name);
        });
    }

}
