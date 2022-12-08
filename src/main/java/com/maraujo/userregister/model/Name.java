package com.maraujo.userregister.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Name {

    public static final String REGEX_FULL_NAME = "^[a-zA-ZJ]+(([',. -][a-zA-ZJ ])?[a-zA-ZJ]*)*$";
    private final String value;

    public Name(String value) {
        inputValidate(value);
        this.value = value;
    }

    private static void inputValidate(String name){
        if (Objects.isNull(name)) throw new IllegalArgumentException();
        if (name.isBlank()) throw new IllegalArgumentException();
        if (!name.trim().contains(" ")) throw new IllegalArgumentException();
        if (!name.matches(REGEX_FULL_NAME)) throw new IllegalArgumentException();
    }

}
