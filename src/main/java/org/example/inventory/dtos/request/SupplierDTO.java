package org.example.inventory.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierDTO {
    @NotBlank(message = "Supplier name is required")
    private String name;

    @NotBlank(message = "Contact information is required")
    private String contactInfo;

    @NotBlank(message = "Address is required")
    private String address;
}
