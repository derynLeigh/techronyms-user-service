package com.techronymsuserservice.service;

import com.techronymsuserservice.DTO.UserDTO;
import com.techronymsuserservice.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    boolean authenticateUser(String username, String password);

    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);
}

