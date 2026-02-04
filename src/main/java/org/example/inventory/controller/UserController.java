package org.example.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inventory.dtos.request.UpdateUserRequest;
import org.example.inventory.dtos.request.UserCreationRequest;
import org.example.inventory.dtos.respon.DataResponse;
import org.example.inventory.dtos.respon.UserResponse;
import org.example.inventory.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //dang ky tai khoan
    @PostMapping("/create")
    public DataResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        UserResponse userResponse = userService.createUser(userCreationRequest);
        return DataResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(userResponse)
                .build();
    }

    @GetMapping("/all")
    public DataResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUser();
        return DataResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all users successfully")
                .data(users)
                .build();
    }
    @GetMapping("/{id}" )
    public DataResponse<UserResponse> getUserById(@PathVariable Long id){
        UserResponse userResponse= userService.getUserById(id);
        return DataResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get user by id successfully")
                .data(userResponse)
                .build();
    }
    @PutMapping()
    public DataResponse<UserResponse> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        UserResponse userResponse= userService.updateUser(updateUserRequest);
        return DataResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Update user successfully")
                .data(userResponse)
                .build();
    }
}
