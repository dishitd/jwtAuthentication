package com.helloworld.jwt.services;

import com.helloworld.jwt.model.User;
import com.helloworld.jwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        MockitoAnnotations.initMocks(this);
        userService.setUserRepository(userRepository);
    }

    @Test
    void givenRoleViewer_whenCallGetUsername_thenReturnUsername() {

        Set<String> userRoles = new HashSet<>();
        userRoles.add("ADMIN");
        User testUser = new User();
        testUser.setUsername("john");
        testUser.setUserRoles(userRoles);
        given(userRepository.findByUsername(anyString())).willReturn(testUser);
        User user = userService.search("john");
        assertEquals("john", user.getUsername());
    }
}
