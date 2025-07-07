package com.bombaycomputers.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.bombaycomputers.user.model.User;
import com.bombaycomputers.user.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import com.bombaycomputers.user.dto.UserRead;
import java.util.stream.Collectors;
import com.bombaycomputers.common.ApiResponse;
import com.bombaycomputers.user.dto.UserCreate;
import com.bombaycomputers.user.mapper.UserMapper;
import jakarta.validation.Valid;



@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/read")
    public ResponseEntity<List<UserRead>> getAllUsers() {
        log.info("Getting all users");
        List<UserRead> userReads = userService.getAllUsers().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(userReads);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserCreate user) {
        userService.createUser(user);
        return ResponseEntity.ok(new ApiResponse("201", "User created successfully", null, "success"));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserRead> getUserById(@PathVariable long id) {
        log.info("Getting user by id: {}", id);
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable long id, @Valid @RequestBody UserCreate user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(new ApiResponse("200", "User updated successfully", null, "success"));
    }

}