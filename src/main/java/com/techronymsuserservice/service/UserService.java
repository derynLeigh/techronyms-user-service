package com.techronymsuserservice.service;

import com.techronymsuserservice.DTO.UserDTO;
import com.techronymsuserservice.model.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);
}

