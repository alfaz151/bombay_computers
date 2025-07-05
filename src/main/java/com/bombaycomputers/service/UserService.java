package com.bombaycomputers.service;

import com.bombaycomputers.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
}