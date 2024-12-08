package pl.slabonart.java_adv_bc_module_4.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.slabonart.java_adv_bc_module_4.exception.UserBirthDateParsingException;
import pl.slabonart.java_adv_bc_module_4.exception.UserNotFoundException;
import pl.slabonart.java_adv_bc_module_4.exception.UserValidationException;
import pl.slabonart.java_adv_bc_module_4.mapper.UserDtoMapper;
import pl.slabonart.java_adv_bc_module_4.model.User;
import pl.slabonart.java_adv_bc_module_4.model.UserDTO;
import pl.slabonart.java_adv_bc_module_4.model.UserRequestDTO;
import pl.slabonart.java_adv_bc_module_4.rerository.UserRepository;
import pl.slabonart.java_adv_bc_module_4.validation.UserValidator;

import java.sql.Date;

@Service("userServiceV2")
public class UserServiceV2 extends DefaultUserService {

    private static final String DATE_PATTERN = "MM/dd/yyyy";

    public UserServiceV2(UserRepository userRepository, UserDtoMapper mapper, @Qualifier("userValidatorV2") UserValidator validator) {
        super(userRepository, mapper, validator);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> mapper.toDto(user, DATE_PATTERN));
    }

    @Override
    public UserDTO getById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .map(user -> mapper.toDto(user, DATE_PATTERN))
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public UserDTO createUser(UserRequestDTO userDto) throws UserValidationException, UserBirthDateParsingException {
        validator.validateUserRequest(userDto);
        User saved = userRepository.save(mapper.toUser(userDto, DATE_PATTERN));
        return mapper.toDto(saved);
    }

    @Override
    public UserDTO updateUser(Long userId, UserRequestDTO userRequestDto) throws UserNotFoundException, UserValidationException {
        validator.validateUserRequest(userRequestDto);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        user.setBirthDate(Date.valueOf(userRequestDto.birthDate()));

        User saved = userRepository.save(user);
        return mapper.toDto(saved, DATE_PATTERN);
    }
}
