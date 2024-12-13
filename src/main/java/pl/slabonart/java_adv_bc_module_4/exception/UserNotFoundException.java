package pl.slabonart.java_adv_bc_module_4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    private static final String MESSAGE = "User with id '%d' not found.";

    public UserNotFoundException(Long userId) {
        super(String.format(MESSAGE, userId));
    }
}
