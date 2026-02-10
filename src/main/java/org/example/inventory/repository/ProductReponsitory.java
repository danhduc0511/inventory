package org.example.inventory.repository;

import org.example.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductReponsitory extends JpaRepository<Product, Long> {
    @Modifying
    @Query(value = """
                UPDATE Product p
                SET p.quantityInStock = p.quantityInStock - :soldQuantity
                WHERE p.id = :id
                  AND p.quantityInStock >= :soldQuantity
            """)
    int decreaseStock(
            @Param("id") Long id,
            @Param("soldQuantity") int soldQuantity
    );

}
