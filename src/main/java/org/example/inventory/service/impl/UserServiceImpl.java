package org.example.inventory.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.UpdateUserRequest;
import org.example.inventory.dtos.request.UserCreationRequest;
import org.example.inventory.dtos.respon.UserResponse;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.models.User;
import org.example.inventory.repository.UserReponsitory;
import org.example.inventory.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j(topic = "USER-SERVICE")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserReponsitory userReponsitory;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createUser(UserCreationRequest registerRequest) {
        log.debug("createUser.....");
        User user= User.builder()
                .fullName(registerRequest.getFullname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(registerRequest.getRole())
                .build();
       User u= userReponsitory.save(user);
       log.info("User created successfully");
        return toUserResponse(u);
    }

    @Override
    public List<UserResponse> getAllUser()  {
        log.info("getAllUser.....");
        List<UserResponse> userResponses=  userReponsitory.findAll().stream()
                .map(this::toUserResponse)
                .toList();
        log.info("getAllUser successfully");
        return userResponses;
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("getUserById.....");
        User user= userReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        log.info("getUserById successfully");
        return toUserResponse(user);
    }

    @Override
    @Transactional()
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        String email=getUserCurrent()
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
        User user=userReponsitory.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        if(updateUserRequest.getFullname()!=null){
            user.setFullName(updateUserRequest.getFullname());
        }
        if(updateUserRequest.getPhoneNumber()!=null){
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }
        User u=userReponsitory.save(user);
        return toUserResponse(u);
    }

    @Override
    public Optional<String> getUserCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null||!authentication.isAuthenticated()||authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        return Optional.ofNullable(authentication.getName());
    }
    public UserResponse toUserResponse(User u){
        return UserResponse.builder()
                .fullname(u.getFullName())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .role(u.getRole())
                .createdAt(u.getCreatedAt())
                .build();
    }

}
