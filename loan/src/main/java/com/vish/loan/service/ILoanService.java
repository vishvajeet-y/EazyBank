package com.vish.loan.service;

import com.vish.loan.dto.LoanDto;
import com.vish.loan.entity.Loan;

import java.util.Optional;

public interface ILoanService {
    void  createLoan(String mobileNumber);
    LoanDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDto loanDto);
    boolean deleteLoan(String mobileNumber);
}
