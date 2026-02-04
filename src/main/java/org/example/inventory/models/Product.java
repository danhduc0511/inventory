package org.example.inventory.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable  = false)
    private String name;

    @Column(nullable  = false)
    private String sku;//SKU = mã định danh duy nhất cho từng sản phẩm trong kho.

    @Column(nullable  = false)
    private BigDecimal price;

    @Column(nullable  = false)
    private Long quantityInStock;

    private String description;

    @Column(nullable  = false,name = "expiration_date")
    @CreationTimestamp
    private LocalDateTime expirationDate;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
//Khai báo các trường: SKU (mã kho), giá, số lượng tồn kho, mô tả, ngày hết hạn.
//
//Thêm Validation cho giá (phải là số dương) và số lượng (tối thiểu là 0).
//
//Thiết lập quan hệ @ManyToOne với Category (nhiều sản phẩm thuộc một danh mục).