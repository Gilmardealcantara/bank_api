package com.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	  @Query(value = "SELECT * FROM transaction WHERE ACC_SEND_ID = ?1 OR ACC_RCV_ID = ?1", nativeQuery = true)
	  List<Transaction> findByAccountId(Long id);
}
