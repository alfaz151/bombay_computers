package com.bombaycomputers.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bombaycomputers.model.User;
import com.bombaycomputers.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import com.bombaycomputers.dto.user.UserRead;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/read")
    public ResponseEntity<List<UserRead>> getAllUsers() {
        log.info("Getting all users");
        List<UserRead> userReads = userService.getAllUsers().stream()
            .map(UserController::toUserRead)
            .collect(Collectors.toList());
        return ResponseEntity.ok(userReads);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserRead> getUserById(@PathVariable long id) {
        log.info("Getting user by id: {}", id);
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toUserRead(user));
    }

    private static UserRead toUserRead(User user) {
        UserRead dto = new UserRead();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.email = user.getEmail();
        dto.phone = user.getPhone();
        dto.address = user.getAddress();
        dto.role = user.getRole();
        dto.isAdmin = user.isAdmin();
        dto.createdAt = user.getCreatedAt();
        return dto;
    }
}