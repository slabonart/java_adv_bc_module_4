package pl.slabonart.java_adv_bc_module_4.exception;

public class UserBirthDateParsingException extends Exception {

    private static final String MESSAGE = "Cannot parse date from: %s.";

    public UserBirthDateParsingException(String value) {
        super(String.format(MESSAGE, value));
    }
}
