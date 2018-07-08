package com.bank.api.controller;

import com.bank.api.exception.ResourceNotFoundException;
import com.bank.api.model.Account;
import com.bank.api.model.Client;
import com.bank.api.model.Transaction;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.ClientRepository;
import com.bank.api.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
/*
class TransactionRequest{
	Long send_id;
	Long rcv_id;
	Double value;
}

class TransactionResponse{
	
	
}
*/
@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    
    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    // Get All Transactions
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Create a new Transaction
    @PostMapping("/transactions")
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
    	Long send_id = transaction.getSend().getId();
    	Long rcv_id = transaction.getRcv().getId();
    	System.out.println(send_id);
    	System.out.println(rcv_id);
    	
        Account acc_send = accountRepository.findById(send_id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", send_id));
        
        Account acc_rcv = accountRepository.findById(rcv_id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", rcv_id));
        
        System.out.println(acc_send);
    	System.out.println(acc_rcv);
    	
        acc_send.setBalance(acc_send.getBalance() - transaction.getValue());
        acc_rcv.setBalance(acc_rcv.getBalance() + transaction.getValue());
        accountRepository.save(acc_send);
    	accountRepository.save(acc_rcv);
        
    	transaction.setSend(acc_send);
        transaction.setRcv(acc_rcv);
    	return transactionRepository.save(transaction);
    }

    // Get all transactions by client id
    @GetMapping("/transactions/{id}")
    public  List<Transaction> getTransactionById(@PathVariable(value = "id") Long accId) {
    	return transactionRepository.findByAccountId(accId);
    }
    
    // Delete a Transaction
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable(value = "id") Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionId));

        transactionRepository.delete(transaction);

        return ResponseEntity.ok().build();
    }

}
