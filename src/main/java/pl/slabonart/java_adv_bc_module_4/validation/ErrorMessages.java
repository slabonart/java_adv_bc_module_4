package pl.slabonart.java_adv_bc_module_4.validation;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String USER_FIRST_NAME_EMPTY_ERROR_MESSAGE = "User first name cannot be empty";
    public static final String USER_FIRST_NAME_NULL_ERROR_MESSAGE = "User first name cannot be null";
    public static final String USER_LAST_NAME_EMPTY_ERROR_MESSAGE = "User last name cannot be empty";
    public static final String USER_LAST_NAME_NULL_ERROR_MESSAGE = "User last name cannot be null";
    public static final String USER_BIRTH_DATE_EMPTY_ERROR_MESSAGE = "User birth date cannot be empty";
    public static final String USER_BIRTH_DATE_NULL_ERROR_MESSAGE = "User birth date cannot be null";
    public static final String USER_BIRTH_DATE_FORMAT_ERROR_MESSAGE = "User birth date should be in format 'dd.MM.yyyy'";
    public static final String USER_BIRTH_DATE_FORMAT_ERROR_MESSAGE_V2 = "User birth date should be in format 'MM/dd/yyyy'";
}
