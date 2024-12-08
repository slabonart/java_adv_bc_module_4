package pl.slabonart.java_adv_bc_module_4.exception;

public class UserValidationException extends Exception {

    private static final String MESSAGE = "User Validation error: %s.";

    public UserValidationException(String errorMessages) {
        super(String.format(MESSAGE, errorMessages));
    }
}
