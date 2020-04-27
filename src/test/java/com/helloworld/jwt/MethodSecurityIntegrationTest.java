package com.helloworld.jwt;

import com.helloworld.jwt.model.User;
import com.helloworld.jwt.repository.UserRepository;
import com.helloworld.jwt.security.JwtTokenProvider;
import com.helloworld.jwt.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration (classes = {
        UserRepository.class,
        ModelMapper.class,
        UserService.class,
        PasswordEncoder.class,
        JwtTokenProvider.class,
        AuthenticationManager.class

})
public class MethodSecurityIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Configuration
    @ComponentScan("com.helloworld.jwt.*")
    public static class SpringConfig {

    }

    @Test
    @WithMockUser(username = "test1", roles = { "TEST" })
    public void givenRoleAdmin_whenCallGetUsername_thenReturnAccessDenied() {
        Set<String> userRoles = new HashSet<>();
        userRoles.add("test");
        given(userRepository.findByUsername(anyString())).willReturn(User.builder()
        .username("test")
                .userRoles(userRoles)
                .build());
        assertEquals(HttpStatus.FORBIDDEN, userService.search("test2"));
    }

}
