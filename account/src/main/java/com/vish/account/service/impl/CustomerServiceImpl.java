package com.vish.account.service.impl;

import com.vish.account.Repository.AccountRepository;
import com.vish.account.Repository.CustomerRepository;
import com.vish.account.dto.*;
import com.vish.account.entity.Accounts;
import com.vish.account.entity.Customer;
import com.vish.account.exception.ResourceNotFoundException;
import com.vish.account.mapper.AccountMapper;
import com.vish.account.mapper.CustomerMapper;
import com.vish.account.service.ICustomerService;
import com.vish.account.service.client.CardFeignClient;
import com.vish.account.service.client.LoanFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardFeignClient cardFeignClient;
    private LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber){
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts=accountRepository.findBycustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
        );
        CustomerDetailsDTO customerDetailsDTO= CustomerMapper.mapToCustomerDetailsDTO(customer,new CustomerDetailsDTO());
        customerDetailsDTO.setAccountDTO(AccountMapper.mapToAccountDTO(accounts,new AccountDTO()));

        ResponseEntity<CardDto> cardDtoResponseEntity=cardFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDTO.setCardDto(cardDtoResponseEntity.getBody());

        ResponseEntity<LoanDto>loanDtoResponseEntity=loanFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDTO.setLoanDto(loanDtoResponseEntity.getBody());

        return customerDetailsDTO;

    }
}
