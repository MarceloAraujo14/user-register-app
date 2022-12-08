package com.maraujo.userregister;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

    @Test
    void namesShouldBeValid() {
        assertDoesNotThrow(() -> new Name("John Doe"));
        assertDoesNotThrow(() -> new Name("Walter White"));
        assertDoesNotThrow(() -> new Name("Jane Smith"));
        assertDoesNotThrow(() -> new Name("O'Connor Johnson"));
        assertDoesNotThrow(() -> new Name("De La Cruz Rodriguez"));

    }
    @Test
    void shouldThrowWhenNameIsNotComplete(){
        assertThrows(InvalidFieldException.class, () -> new Name("John"));
    }
    @Test
    void shouldThrowWhenNameHaveIsNumber(){
        assertThrows(InvalidFieldException.class, () -> new Name("a123"));
    }
    @Test
    void shouldThrowWhenNameHasSpecialCharacters(){
        assertThrows(InvalidFieldException.class, () -> new Name("!@#"));
    }
    @Test
    void shouldThrowWhenNameIsEmpty(){
        assertThrows(InvalidFieldException.class, () -> new Name(" "));
    }
    @Test
    void shouldThrowWhenNameIsNull(){
        assertThrows(InvalidFieldException.class, () -> new Name(null));
    }


}
