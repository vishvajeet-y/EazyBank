package com.vish.loan.mapper;

import com.vish.loan.dto.LoanDto;
import com.vish.loan.entity.Loan;

public class LoanMapper {
    //it will map data from Loan to LoanDto
    public static LoanDto mapToLoanDto(Loan loan ,LoanDto loanDto){
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmmountPaid(loan.getAmmountPaid());
        loanDto.setOutStandingAmmount(loan.getOutStandingAmmount());
        return loanDto;

    }
    //it will map data from LoanDto to Loan
    public  static Loan mapToLoan(LoanDto loanDto,Loan loan){
          loan.setMobileNumber(loanDto.getMobileNumber());
          loan.setLoanNumber(loanDto.getLoanNumber());
          loan.setLoanType(loanDto.getLoanType());
          loan.setTotalLoan(loanDto.getTotalLoan());
          loan.setAmmountPaid(loanDto.getAmmountPaid());
          loan.setOutStandingAmmount(loanDto.getOutStandingAmmount());
          return loan;
    }
}
 /*
        To save data using repository interface,we need to send object of Loan entity not loanDto.
        We will have logic which will convert DTO to entity class.
         */