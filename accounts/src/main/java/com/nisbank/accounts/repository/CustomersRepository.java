package com.nisbank.accounts.repository;

import com.nisbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);

}
