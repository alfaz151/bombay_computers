package com.bombaycomputers.user.service;

import org.springframework.stereotype.Service;
import com.bombaycomputers.user.service.UserService;
import com.bombaycomputers.user.model.User;
import com.bombaycomputers.user.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import com.bombaycomputers.user.dto.UserCreate;
import com.bombaycomputers.user.mapper.UserMapper;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(UserCreate user) {
        User userEntity = userMapper.toEntity(user);
        boolean isUserExists = userRepository.findByEmail(userEntity.getEmail()).isPresent();
        
        log.info("User exists: {}", isUserExists);
        if(isUserExists) {
            throw new RuntimeException("User already exists");
        }

        return userRepository.save(userEntity);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(long id, UserCreate user) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userEntity.setId(id);
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setAddress(user.getAddress());
        
        return userRepository.save(userEntity);
    }   

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


}