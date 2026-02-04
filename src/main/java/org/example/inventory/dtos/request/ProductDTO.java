package org.example.inventory.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "product sku is required")
    private String sku;

    @NotNull(message = "product price is required")
    private BigDecimal price;

    @NotNull(message = "Product quantityInstock is required")
    private Long quantityInStock;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotNull(message =  "Category id is required")
    private  Long categoryId;

}
