package com.vish.loan.service.impl;

import com.vish.loan.constants.LoanConstants;
import com.vish.loan.dto.LoanDto;
import com.vish.loan.entity.Loan;
import com.vish.loan.exception.LoanAlreadyExistException;
import com.vish.loan.exception.ResourceNotFoundException;
import com.vish.loan.mapper.LoanMapper;
import com.vish.loan.repository.loanRepository;
import com.vish.loan.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private loanRepository  loanRepository;


    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan>OptionalLoan=loanRepository.findByMobileNumber(mobileNumber);
        if(OptionalLoan.isPresent())
        {
            throw new LoanAlreadyExistException("Loan already registered with given mobilenumber "+mobileNumber);
            /*
            It will throw LoanAlreadyExisting exception to my parent method which is inside controller
            Inside controller we can handle this exception using try-catch block as per our understanding.

             */
        }


       //save new Loan
        loanRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {

        /*Optional<Loan>OptionalLoan=loanRepository.findByMobileNumber(mobileNumber);
        if(OptionalLoan.isEmpty())
        {
            throw new LoanNotExist("No Loan Exist for given mobile number "+mobileNumber);
        }
        Loan loan=OptionalLoan.get()
        This code can be single written in
         */

        Loan loan=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));

        LoanDto loanDto=LoanMapper.mapToLoanDto(loan,new LoanDto());

     return  loanDto;


    }

    @Override
    public boolean  updateLoan(LoanDto loanDto) {
        Loan loan=loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(()->new ResourceNotFoundException("Loan","LoanNumber",loanDto.getLoanNumber()));
        LoanMapper.mapToLoan(loanDto,loan);
        loanRepository.save(loan);
        return true;

    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan=loanRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
        loanRepository.delete(loan);
        return true;
    }

    private Loan createNewLoan(String mobileNumber){
        Loan newLoan=new Loan();
        long randomNumber=100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmmountPaid(0);
        newLoan.setOutStandingAmmount(LoanConstants.NEW_LOAN_LIMIT);
        return  newLoan;


    }

}
/*
  --- All Buisness logic should always be written inside service layer...
 */
