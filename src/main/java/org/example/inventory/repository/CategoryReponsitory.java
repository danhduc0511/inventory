package org.example.inventory.repository;

import org.example.inventory.models.Category;
import org.example.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReponsitory  extends JpaRepository<Category,Long> {
}
