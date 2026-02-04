package org.example.inventory.dtos.respon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String name;

    private String sku;

    private BigDecimal price;

    private Long quantityInstock;

    private String description;

    private  String categoryName;
}
