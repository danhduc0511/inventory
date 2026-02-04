package org.example.inventory.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.inventory.enums.TransactionStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionUpdateDTO {
    @NotBlank(message = "Transaction name is required")
    private TransactionStatus status;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
