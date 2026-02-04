package org.example.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.SignIn;
import org.example.inventory.models.User;
import org.example.inventory.service.AuthService;
import org.example.inventory.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public String signIn(SignIn signIn) {
        log.info("signIn.............");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signIn.getUsername(), signIn.getPassword()
        ));
        User user= (User) authentication.getPrincipal();
        log.info("signIn successfully : " + user.getFullName());
        String token= jwtUtil.generateToken(user);
        return token;
    }
}
