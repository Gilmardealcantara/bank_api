package com.bank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.api.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
