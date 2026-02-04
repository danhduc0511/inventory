package org.example.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inventory.dtos.request.SignIn;
import org.example.inventory.dtos.respon.DataResponse;
import org.example.inventory.dtos.respon.TokenResponse;
import org.example.inventory.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    //Sign in
    @PostMapping("/login")
    public DataResponse<TokenResponse> signIn(@RequestBody @Valid SignIn signIn){
        String token = authService.signIn(signIn);
        TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(token)
                .build();
        return DataResponse.<TokenResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Sign in successful")
                .data(tokenResponse)
                .build();
    }

}
