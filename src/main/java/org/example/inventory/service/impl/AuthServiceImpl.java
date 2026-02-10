package org.example.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.SignIn;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.models.User;
import org.example.inventory.repository.RedisTokenRepository;
import org.example.inventory.service.AuthService;
import org.example.inventory.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisTokenRepository redisTokenRepository;

    @Override
    public String signIn(SignIn signIn) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signIn.getUsername(), signIn.getPassword()
            ));
            User user = (User) authentication.getPrincipal();
            log.info("User [{}] signed in successfully", user.getUsername());
            return jwtUtil.generateToken(user);
        } catch (AuthenticationException e) {
            log.warn("Authentication failed for user: {} - Error: {}", signIn.getUsername(), e.getMessage());
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    @Override
    public void logout(String token) {

    }
}
