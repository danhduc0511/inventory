package org.example.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inventory.dtos.request.TransactionCreateRequest;
import org.example.inventory.dtos.request.TransactionUpdateDTO;
import org.example.inventory.dtos.respon.DataResponse;
import org.example.inventory.dtos.respon.TransactionResponse;
import org.example.inventory.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/create")
    public DataResponse<TransactionResponse> createTransaction(@RequestBody @Valid TransactionCreateRequest transactionCreateRequest) {
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionCreateRequest);
        return DataResponse.<TransactionResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Transaction created successfully")
                .data(transactionResponse)
                .build();
    }
    @GetMapping("/by-user")
    public DataResponse<List<TransactionResponse>> getAllTransactionsByUserId(@RequestParam Long userId) {
            List<TransactionResponse> transactions  = transactionService.getTransactionsByUserId(userId);
            return DataResponse.<List<TransactionResponse>>builder()
                    .code(HttpStatus.OK.value())
                    .message("Get all transactions successfully")
                    .data(transactions)
                    .build();
    }
    @PutMapping("/update/{id}")
    public DataResponse<TransactionResponse> updateTransaction(@PathVariable Long id, @RequestBody @Valid TransactionUpdateDTO upd) {
        TransactionResponse transactionResponse = transactionService.updateTransaction(id, upd);
        return DataResponse.<TransactionResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Transaction updated successfully")
                .data(transactionResponse)
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public DataResponse<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return DataResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Transaction deleted successfully")
                .build();
    }
}
