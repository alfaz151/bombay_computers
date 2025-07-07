package com.bombaycomputers.user.mapper;

import com.bombaycomputers.user.model.User;
import com.bombaycomputers.user.dto.UserCreate;
import com.bombaycomputers.user.dto.UserRead;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public User toEntity(UserCreate dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
    
    public UserRead toDto(User user) {
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