package org.example.inventory.dtos.respon;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TokenResponse {
    private String accessToken;
}
