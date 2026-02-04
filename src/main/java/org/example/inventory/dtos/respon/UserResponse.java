package org.example.inventory.dtos.respon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.inventory.enums.UserRole;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String fullname;

    private String email;

    private String phoneNumber;

    private UserRole role;

    private LocalDateTime createdAt;
}
