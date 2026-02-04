package org.example.inventory.service;

import org.example.inventory.dtos.request.SignIn;

public interface AuthService {
    public String signIn(SignIn signIn);
}
