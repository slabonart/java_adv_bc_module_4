package pl.slabonart.java_adv_bc_module_4.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
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

@Service("defaultUserService")
@AllArgsConstructor
@Primary
public class DefaultUserService implements UserService {

    UserRepository userRepository;

    UserDtoMapper mapper;

    UserValidator validator;

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public UserDTO getById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public UserDTO createUser(UserRequestDTO userDto) throws UserValidationException, UserBirthDateParsingException {
        validator.validateUserRequest(userDto);
        User saved = userRepository.save(mapper.toUser(userDto));
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
        return mapper.toDto(saved);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
