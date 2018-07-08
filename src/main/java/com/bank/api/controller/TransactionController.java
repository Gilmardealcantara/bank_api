package com.bank.api.controller;

import com.bank.api.exception.ResourceNotFoundException;
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
    	
        Client cli_send = clientRepository.findById(send_id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", send_id));
        
        Client cli_rcv = clientRepository.findById(rcv_id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", rcv_id));
        
        System.out.println(cli_send);
    	System.out.println(cli_rcv);
    	
        cli_send.getAccount().setBalance(cli_send.getAccount().getBalance() - transaction.getValue());
        cli_rcv.getAccount().setBalance(cli_rcv.getAccount().getBalance() + transaction.getValue());
        clientRepository.save(cli_send);
    	clientRepository.save(cli_rcv);
        
    	transaction.setSend(cli_send);
        transaction.setRcv(cli_rcv);
    	return transactionRepository.save(transaction);
    }

    // Get all transactions by client id
    @GetMapping("/transactions/{id}")
    public  List<Transaction> getTransactionById(@PathVariable(value = "id") Long clientId) {
    	return transactionRepository.findByClientId(clientId);
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
