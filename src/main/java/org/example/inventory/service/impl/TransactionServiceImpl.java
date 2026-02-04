package org.example.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.inventory.dtos.request.TransactionCreateRequest;
import org.example.inventory.dtos.request.TransactionUpdateDTO;
import org.example.inventory.dtos.respon.TransactionResponse;
import org.example.inventory.enums.TransactionStatus;
import org.example.inventory.exception.AppException;
import org.example.inventory.exception.ErrorCode;
import org.example.inventory.mapper.TransactionMapper;
import org.example.inventory.models.Product;
import org.example.inventory.models.Supplier;
import org.example.inventory.models.Transaction;
import org.example.inventory.models.User;
import org.example.inventory.repository.ProductReponsitory;
import org.example.inventory.repository.SupplierReponsitory;
import org.example.inventory.repository.TransactionReponsitory;
import org.example.inventory.repository.UserReponsitory;
import org.example.inventory.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionReponsitory reponsitory;
    private final UserReponsitory userReponsitory;
    private final SupplierReponsitory supplierReponsitory;
    private final ProductReponsitory productReponsitory;
    private final TransactionReponsitory transactionReponsitory;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponse createTransaction(TransactionCreateRequest transactionCreateRequest) {
        log.info("Transaction created---------------------------");
        //1: validate request
        User existingUser=userReponsitory.findById(transactionCreateRequest.getUserId())
                .orElseThrow(() -> new  AppException(ErrorCode.USER_NOT_EXIST));
        Product existingProduct= productReponsitory.findById(transactionCreateRequest.getProductId())
                .orElseThrow(() -> new  AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Supplier existingSupplier= supplierReponsitory.findById(transactionCreateRequest.getSupplierId())
                .orElseThrow(() -> new  AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        //2: Business logic
        int rows= productReponsitory.decreaseStock(transactionCreateRequest.getProductId(), transactionCreateRequest.getTotalProducts());
        if(rows==0){
            throw new AppException(ErrorCode.INSUFFICIENT_STOCK);
        }
        BigDecimal totalPrice= existingProduct.getPrice().multiply(BigDecimal.valueOf(transactionCreateRequest.getTotalProducts()));
        Transaction transaction = Transaction.builder()
                .name(transactionCreateRequest.getName())
                .totalProducts(transactionCreateRequest.getTotalProducts())
                .totalPrice(totalPrice)
                .transactionType(transactionCreateRequest.getTransactionType())
                .status(TransactionStatus.PENDING)
                .description(transactionCreateRequest.getDescription())
                .user(existingUser)
                .product(existingProduct)
                .supplier(existingSupplier)
                .build();
        //3: Save to DB
        Transaction savedTransaction= transactionReponsitory.save(transaction);
        log.info("Transaction created successfully");
        return transactionMapper.toResponse(savedTransaction);
    }

    @Override
    public List<TransactionResponse> getTransactionsByUserId(Long userId) {
        log.info("getTransactionsByUserId-------------------------");
        List<TransactionResponse> transactionReponsitory= reponsitory.findAllByUserId(userId).stream()
                .map(transactionMapper::toResponse)
                .toList();
        log.info("getTransactionsByUserId successfully");
        return transactionReponsitory;
    }

    @Override
    public TransactionResponse updateTransaction(Long id,TransactionUpdateDTO transactionUpdateDTO) {
        log.info("updateTransaction---------------------------");
        Transaction existingTransaction= reponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TRANSACTION_NOT_FOUND));
        if(transactionUpdateDTO.getStatus()!=null) {
            existingTransaction.setStatus(transactionUpdateDTO.getStatus());
        }
        if(transactionUpdateDTO.getDescription()!=null) {
            existingTransaction.setDescription(transactionUpdateDTO.getDescription());
        }
        Transaction updatedTransaction= reponsitory.save(existingTransaction);
        log.info("updateTransaction successfully");
        return transactionMapper.toResponse(updatedTransaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(Long transactionId) {
       log.info("delete Transaction with id: {}-----------------",transactionId);
       Transaction existingTransaction= reponsitory.findById(transactionId)
               .orElseThrow(() -> new AppException(ErrorCode.TRANSACTION_NOT_FOUND));
       reponsitory.delete(existingTransaction);
         log.info("delete Transaction with id: {} successfully",transactionId);
    }
}
