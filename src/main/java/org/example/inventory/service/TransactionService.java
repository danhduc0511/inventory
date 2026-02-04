package org.example.inventory.service;

import org.example.inventory.dtos.request.TransactionCreateRequest;
import org.example.inventory.dtos.request.TransactionUpdateDTO;
import org.example.inventory.dtos.respon.TransactionResponse;

import java.util.List;

public interface TransactionService {
    public TransactionResponse createTransaction(TransactionCreateRequest transactionCreateRequest);
    public List<TransactionResponse> getTransactionsByUserId(Long userId);
    public TransactionResponse updateTransaction(Long id  ,TransactionUpdateDTO transactionUpdateDTO);
    public void deleteTransaction(Long transactionId);
}
