package org.example.inventory.dtos.respon;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SignInResponse {
    private Long userId;
    private String accessToken;
}
