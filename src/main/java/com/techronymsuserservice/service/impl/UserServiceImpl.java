package com.techronymsuserservice.service.impl;

import com.techronymsuserservice.DTO.UserDTO;
import com.techronymsuserservice.exceptions.UserAlreadyExistsException;
import com.techronymsuserservice.model.User;
import com.techronymsuserservice.respository.UserRepository;
import com.techronymsuserservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        user.setUsername(user.getUsername());
        if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        user.setEmail(user.getEmail().toLowerCase());
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }
}
