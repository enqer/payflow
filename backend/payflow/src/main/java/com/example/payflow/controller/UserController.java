package com.example.payflow.controller;

import com.example.payflow.dto.PasswordDTO;
import com.example.payflow.dto.UserDTO;
import com.example.payflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class responsible for managing operations related to users.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    /**
     * Service for handling user operations.
     */
    private final UserService userService;

    /**
     * Retrieves a user by their ID using a HTTP GET request.
     *
     * @param id User ID to retrieve.
     * @return ResponseEntity with the {@link UserDTO} if found, else returns a 204 status.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if(user != null)
            return ResponseEntity.ok(user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a list of all users using a HTTP GET request.
     *
     * @return List of {@link UserDTO} objects representing all users.
     */
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Changes the password for a user using a HTTP PATCH request.
     *
     * @param id       User ID for whom to change the password.
     * @param password PasswordDTO containing the new password.
     * @return ResponseEntity with a success message if the password is successfully changed, else returns a 400 status.
     */
    @PatchMapping("/users/{id}/password")
    public ResponseEntity<?> changeUserPassword(@PathVariable Long id, @RequestBody PasswordDTO password){
        UserDTO user = userService.changeUserPassword(id,password);
        if(user != null)
            return new ResponseEntity("Password successfully changed.", HttpStatus.OK);
        return ResponseEntity.badRequest().build();
    }
}
