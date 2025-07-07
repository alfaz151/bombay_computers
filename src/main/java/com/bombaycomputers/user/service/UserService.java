package com.bombaycomputers.user.service;

import com.bombaycomputers.user.dto.UserCreate;
import com.bombaycomputers.user.model.User;
import java.util.List;

public interface UserService {
    User createUser(UserCreate user);
    User getUserById(long id);
    User updateUser(long id, UserCreate user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}