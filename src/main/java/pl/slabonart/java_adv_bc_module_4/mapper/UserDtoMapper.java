package pl.slabonart.java_adv_bc_module_4.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.slabonart.java_adv_bc_module_4.exception.UserBirthDateParsingException;
import pl.slabonart.java_adv_bc_module_4.model.User;
import pl.slabonart.java_adv_bc_module_4.model.UserDTO;
import pl.slabonart.java_adv_bc_module_4.model.UserRequestDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd.MM.yyyy")
    User toUser(UserRequestDTO dto);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd.MM.yyyy")
    UserDTO toDto(User user);

    @Mapping(target = "birthDate", expression = "java(parseDate(dto.birthDate(), datePattern))")
    User toUser(UserRequestDTO dto, String datePattern) throws UserBirthDateParsingException;

    @Mapping(target = "birthDate", expression = "java(formatDate(user.getBirthDate(), datePattern))")
    UserDTO toDto(User user, String datePattern);

    default String formatDate(Date date, String datePattern) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(datePattern).format(date);
    }

    default Date parseDate(String dateString, String datePattern) throws UserBirthDateParsingException {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat(datePattern).parse(dateString);
        } catch (ParseException e) {
            throw new UserBirthDateParsingException(dateString);
        }
    }
}
