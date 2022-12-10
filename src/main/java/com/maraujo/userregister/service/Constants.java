package com.maraujo.userregister.service;

public class Constants {

    private Constants(){}

    public static class ErrorMessage {

        private ErrorMessage() {}

        public static final String ERROR_MSG_CANNOT_BE_EMPTY = "Field cannot be empty";
        public static final String ERROR_MSG_CANNOT_BE_NULL = "Field cannot be null";
        public static final String ERROR_MSG_NAME_NOT_VALID = "Field name must contain a name and at least one surname";
        public static final String ERROR_MSG_BIRTH_DATE_BEFORE_AFTER = "Date of birth must be between 1 and 100 years ago";
        public static final String ERROR_MSG_BIRTH_DATE_FORMAT = "Field Birth Date should be format as dd/MM/yyyy";
        public static final String ERROR_MSG_CPF_INVALID = "Inform a valid CPF";
        public static final String ERROR_MSG_INVALID_POSTALCODE = "Insert a valid postalCode";
        public static final String ERROR_MSG_INVALID_PHONENUMBER = "Insert a valid phoneNumber";
        public static final String ERROR_MSG_INVALID_EMAIL = "Insert a valid email";
    }
}
