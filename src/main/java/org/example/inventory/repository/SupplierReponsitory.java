package org.example.inventory.repository;

import org.example.inventory.models.Product;
import org.example.inventory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierReponsitory extends JpaRepository<Supplier,Long> {
}
