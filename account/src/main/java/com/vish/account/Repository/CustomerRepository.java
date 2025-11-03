package com.vish.account.Repository;

import com.vish.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaRepository will give us basic CRUD application instead of manually creating them.
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer>findByMobileNumber(String mobileNumber);
}
