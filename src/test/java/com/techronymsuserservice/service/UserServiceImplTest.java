package com.techronymsuserservice.service;

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
}
