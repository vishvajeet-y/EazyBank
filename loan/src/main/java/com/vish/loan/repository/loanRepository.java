package com.vish.loan.repository;

import com.vish.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface loanRepository extends JpaRepository<Loan,Long> {
    //additional method
    Optional<Loan>findByMobileNumber(String mobileNumber);
    Optional<Loan>findByLoanNumber(String loanNumber);
}
/*
@Repository tells Spring that it is Data Access Layer Component-- responsible for interacting with the database
It is interface ,we don't write code ,Spring Data JPA generate automatically at runtime.
 extends JpaRepository<T, ID>
 This means that our interface inherit all CRUD operation provided by JpaRepository
 T--is our entity class
 ID--The type of primary key
 | Method           | Description                                   |
| ---------------- | --------------------------------------------- |
| `save(entity)`   | Save or update a record                       |
| `findById(id)`   | Find by primary key, returns `Optional<User>` |
| `findAll()`      | Returns all users                             |
| `deleteById(id)` | Delete user by ID                             |
| `count()`        | Count all records                             |
| `existsById(id)` | Check if record exists                        |

 */