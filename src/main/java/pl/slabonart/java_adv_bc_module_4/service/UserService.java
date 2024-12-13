package pl.slabonart.java_adv_bc_module_4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.slabonart.java_adv_bc_module_4.exception.UserBirthDateParsingException;
import pl.slabonart.java_adv_bc_module_4.exception.UserNotFoundException;
import pl.slabonart.java_adv_bc_module_4.exception.UserValidationException;
import pl.slabonart.java_adv_bc_module_4.model.UserDTO;
import pl.slabonart.java_adv_bc_module_4.model.UserRequestDTO;


public interface UserService {

    Page<UserDTO> findAll(Pageable pageable);

    UserDTO getById(Long userId) throws UserNotFoundException;

    UserDTO createUser(UserRequestDTO userRequestDto) throws UserValidationException, UserBirthDateParsingException;

    UserDTO updateUser(Long userId, UserRequestDTO userRequestDto) throws UserNotFoundException, UserValidationException;

    void deleteUser(Long userId);

}
