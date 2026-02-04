package org.example.inventory.repository;

import org.example.inventory.dtos.respon.TransactionResponse;
import org.example.inventory.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionReponsitory  extends JpaRepository<Transaction,Long> {
    @Query("SELECT t " +
            "FROM Transaction t WHERE t.user.id = :userId")
    List<Transaction> findAllByUserId(@Param("userId") Long  userId);

}
