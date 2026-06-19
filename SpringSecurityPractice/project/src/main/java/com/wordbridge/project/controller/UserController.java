package com.wordbridge.project.controller;

import com.wordbridge.project.dto.requestdto.UserRequestDTO;
import com.wordbridge.project.dto.responsedto.UserResponseDTO;

import com.wordbridge.project.service.UserService;


import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {



    private final UserService userService;

    // Register User
    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> register(
            @RequestBody UserRequestDTO dto) {

        UserResponseDTO user = userService.register(dto);

        return ResponseEntity.ok(user);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    // Get User By id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(
                userService.updateUser(id, dto)
        );
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User Deleted Successfully");
    }

}
