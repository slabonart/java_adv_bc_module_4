package pl.slabonart.java_adv_bc_module_4.validation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.slabonart.java_adv_bc_module_4.exception.UserValidationException;
import pl.slabonart.java_adv_bc_module_4.model.UserRequestDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_BIRTH_DATE_EMPTY_ERROR_MESSAGE;
import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_BIRTH_DATE_FORMAT_ERROR_MESSAGE;
import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_BIRTH_DATE_NULL_ERROR_MESSAGE;
import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_FIRST_NAME_EMPTY_ERROR_MESSAGE;
import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_FIRST_NAME_NULL_ERROR_MESSAGE;
import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_LAST_NAME_EMPTY_ERROR_MESSAGE;
import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_LAST_NAME_NULL_ERROR_MESSAGE;

@Component
@Primary
public class UserValidator {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    public void validateUserRequest(UserRequestDTO userRequestDTO) throws UserValidationException {
        List<String> validationErrors = new ArrayList<>();

        validateUserFirstName(userRequestDTO.firstName(), validationErrors);
        validateUserLastName(userRequestDTO.lastName(), validationErrors);
        validateUserBirthDate(userRequestDTO.birthDate(), validationErrors);

        if (!validationErrors.isEmpty()) {
            throw new UserValidationException(String.join(", ", validationErrors));
        }
    }

    private void validateUserFirstName(String userFirstName, List<String> validationErrors) {
        if (userFirstName == null) {
            validationErrors.add(USER_FIRST_NAME_NULL_ERROR_MESSAGE);
        } else if (userFirstName.isEmpty()) {
            validationErrors.add(USER_FIRST_NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateUserLastName(String userLastName, List<String> validationErrors) {
        if (userLastName == null) {
            validationErrors.add(USER_LAST_NAME_NULL_ERROR_MESSAGE);
        } else if (userLastName.isEmpty()) {
            validationErrors.add(USER_LAST_NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateUserBirthDate(String birthDate, List<String> validationErrors) {
        if (birthDate == null) {
            validationErrors.add(USER_BIRTH_DATE_NULL_ERROR_MESSAGE);
        } else if (birthDate.isEmpty()) {
            validationErrors.add(USER_BIRTH_DATE_EMPTY_ERROR_MESSAGE);
        }
        validateDateFormat(birthDate, validationErrors);
    }

    protected void validateDateFormat(String birthDate, List<String> validationErrors) {
        try {
            formatter.parse(birthDate);
        } catch (ParseException e) {
            validationErrors.add(USER_BIRTH_DATE_FORMAT_ERROR_MESSAGE);
        }
    }
}
