package org.example.inventory.service;

import org.example.inventory.dtos.request.UpdateUserRequest;
import org.example.inventory.dtos.request.UserCreationRequest;
import org.example.inventory.dtos.respon.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(UserCreationRequest registerRequest);
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
    UserResponse updateUser(UpdateUserRequest updateUserRequest);
    Optional<String> getUserCurrent();
}
