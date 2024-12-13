package pl.slabonart.java_adv_bc_module_4.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.slabonart.java_adv_bc_module_4.exception.UserBirthDateParsingException;
import pl.slabonart.java_adv_bc_module_4.exception.UserNotFoundException;
import pl.slabonart.java_adv_bc_module_4.exception.UserValidationException;
import pl.slabonart.java_adv_bc_module_4.model.UserDTO;
import pl.slabonart.java_adv_bc_module_4.model.UserRequestDTO;
import pl.slabonart.java_adv_bc_module_4.service.UserService;

@RestController
@RequestMapping(value = "api/v2/users")
public class UserControllerV2 {

    private final UserService userService;

    public UserControllerV2(@Qualifier("userServiceV2") UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Page<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDTO> userPage = userService.findAll(pageable);
        return ResponseEntity.ok(userPage);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable Long userId
    ) throws UserNotFoundException {
        UserDTO user = userService.getById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequestDTO userRequestDto) throws UserValidationException, UserBirthDateParsingException {
        UserDTO userDTO = userService.createUser(userRequestDto);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO userRequestDto) throws UserNotFoundException, UserValidationException {
        UserDTO updated = userService.updateUser(userId, userRequestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
