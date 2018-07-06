package com.bank.api.controller;

import com.bank.api.exception.ResourceNotFoundException;
import com.bank.api.model.Transaction;
import com.bank.api.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    // Get All Transactions
    @GetMapping("/trasactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Create a new Transaction
    @PostMapping("/trasaction")
    public Transaction createTransaction(@Valid @RequestBody Transaction trasaction) {
        return transactionRepository.save(trasaction);
    }

    // Get a Single Transaction
    @GetMapping("/trasactions/{id}")
    public Transaction getTransactionById(@PathVariable(value = "id") Long trasactionId) {
        return transactionRepository.findById(trasactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", trasactionId));
    }
    
    // Delete a Transaction
    @DeleteMapping("/trasactions/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable(value = "id") Long trasactionId) {
        Transaction trasaction = transactionRepository.findById(trasactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", trasactionId));

        transactionRepository.delete(trasaction);

        return ResponseEntity.ok().build();
    }

}
