package com.maraujo.userregister.form;

public class Constants {
    public static class ErrorMessage {
        public static final String ERROR_MSG_NAME_NOT_VALID = "Field name must contain a name and at least one surname";
        public static final String ERROR_MSG_NAME_CANNOT_BE_EMPTY = "Field name cannot be empty";
        public static final String ERROR_MSG_NAME_CANNOT_BE_NULL = "Field name cannot be null";
        public static final String ERROR_MSG_BIRTH_DATE_BEFORE_AFTER = "Date of birth must be between 1 and 100 years ago";
        public static final String ERROR_MSG_BIRTH_DATE_NULL = "Field Birth Date cannot be null";
        public static final String ERROR_MSG_BIRTH_DATE_EMPTY = "Field Birth Date cannot be empty";
        public static final String ERROR_MSG_BIRTH_DATE_FORMAT = "Field Birth Date should be format as dd/MM/yyyy";
    }
}
