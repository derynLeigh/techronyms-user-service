package com.techronymsuserservice.service;

import com.techronymsuserservice.exceptions.UserAlreadyExistsException;
import com.techronymsuserservice.model.*;
import com.techronymsuserservice.respository.UserRepository;
import com.techronymsuserservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testRegisterUser() {
        User user = new User("testUser", "test@example.com", "password123", UserRole.USER);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        when(userRepository.save(ArgumentMatchers.any())).thenAnswer(invocation -> invocation.getArgument(0));

        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("encodedPassword", registeredUser.getPassword());
        assertEquals("test@example.com", registeredUser.getEmail());
        assertEquals(UserRole.USER, registeredUser.getRole());
    }

    @Test
    public void testExceptionThrownWhenUsernameExists() {
        User existingUser = new User("existingUser", "already@exists.com", "password123", UserRole.USER);

        when(userRepository.findByUsername("existingUser")).thenReturn(List.of(existingUser));

        User newUser = new User("existingUser", "new@example.com", "password321", UserRole.USER);

        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(newUser));
        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void testExceptionThrownWhenEmailExists() {
        User existingUser = new User("user2", "existing@example.com", "password123", UserRole.USER);
        when(userRepository.findByEmail("existing@example.com")).thenReturn(existingUser);

        User newUser = new User("newUser", "existing@example.com", "password123", UserRole.USER);

        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(newUser));
        assertEquals("Email already exists", exception.getMessage());
    }
}
