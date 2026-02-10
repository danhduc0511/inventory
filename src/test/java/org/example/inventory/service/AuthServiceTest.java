package org.example.inventory.service;

import org.example.inventory.dtos.request.SignIn;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.models.User;
import org.example.inventory.service.impl.AuthServiceImpl;
import org.example.inventory.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void singIn_Success() {
        // Given
        SignIn signIn = new SignIn();
        signIn.setUsername("testuser");
        signIn.setPassword("123456");
        User user = new User();
        user.setEmail("testuser");
        // When
        when(authenticationManager.authenticate(
                any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtUtil.generateToken(user)).thenReturn("token");
        String token = authService.signIn(signIn);
        // Then
        assertNotNull(token);
        assertEquals("token", token);
    }
    @Test
    void signIn_Failure() {
        // Given
        SignIn signIn = new SignIn();
        signIn.setUsername("wronguser");
        signIn.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // When & Then
        AppException exception = assertThrows(
                AppException.class,
                () -> authService.signIn(signIn)
        );

        assertEquals(ErrorCode.INVALID_CREDENTIALS, exception.getErrorCode());
    }
}
