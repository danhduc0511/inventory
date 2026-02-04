package org.example.inventory.dtos.respon;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.inventory.enums.TransactionStatus;
import org.example.inventory.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    private Long id;
    private String name;
    private Integer totalProducts;
    private BigDecimal totalPrice;
    private TransactionType transactionType;
    private TransactionStatus status; //pending, completed, processing
    private String description;
    private LocalDateTime createdAt;
    private Long userId;
    private Long supplierId;
    private Long productId;
}
