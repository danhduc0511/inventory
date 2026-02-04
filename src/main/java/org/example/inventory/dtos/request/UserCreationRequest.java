package org.example.inventory.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.inventory.enums.UserRole;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//Nếu field nào null thì ẩn đi
@JsonIgnoreProperties(ignoreUnknown = true)//Nếu có field lạ trong json thì bỏ qua ko lỗi
public class UserCreationRequest {


    @NotBlank(message = "Name is required")
    private String fullname;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;


    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private UserRole role;

}
//id,name,email,password,phone_number,role,transactions,created_at