package org.example.inventory.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.inventory.enums.TransactionStatus;
import org.example.inventory.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer totalProducts;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // pruchase, sale, return

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; //pending, completed, processing

    private String description;

    @CreationTimestamp
    private  LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 1. Trỏ vào cột user_id
    private User user;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false) // Đổi user_id -> supplier_id
    private Supplier supplier;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Đổi user_id -> product_id
    private Product product;
}
