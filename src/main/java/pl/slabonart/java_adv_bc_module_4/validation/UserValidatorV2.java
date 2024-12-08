package pl.slabonart.java_adv_bc_module_4.validation;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static pl.slabonart.java_adv_bc_module_4.validation.ErrorMessages.USER_BIRTH_DATE_FORMAT_ERROR_MESSAGE_V2;

@Component("userValidatorV2")
public class UserValidatorV2 extends UserValidator {

    private final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

    @Override
    protected void validateDateFormat(String birthDate, List<String> validationErrors) {
        try {
            formatter.parse(birthDate);
        } catch (ParseException e) {
            validationErrors.add(USER_BIRTH_DATE_FORMAT_ERROR_MESSAGE_V2);
        }
    }
}
