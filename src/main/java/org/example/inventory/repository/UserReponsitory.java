package org.example.inventory.repository;

import org.example.inventory.models.Product;
import org.example.inventory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReponsitory  extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
}
