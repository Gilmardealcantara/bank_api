package com.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	  @Query(value = "SELECT * FROM transaction WHERE CLI_SEND_ID = ?1", nativeQuery = true)
	  List<Transaction> findByClientId(Long id);
}
