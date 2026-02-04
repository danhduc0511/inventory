package org.example.inventory.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.inventory.enums.TransactionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionCreateRequest {

    @NotBlank(message = "Transaction name is required")
    @Size(max = 255, message = "Transaction name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Total products is required")
    @Min(value = 1, message = "Total products must be at least 1")
    private Integer totalProducts;

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Product ID is required")
    private Long productId;


}
