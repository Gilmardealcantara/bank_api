package com.bank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.api.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
