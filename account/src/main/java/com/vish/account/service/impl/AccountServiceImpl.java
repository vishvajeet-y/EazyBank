package com.vish.account.service.impl;

import com.vish.account.Repository.AccountRepository;
import com.vish.account.Repository.CustomerRepository;
import com.vish.account.constants.AccountConstants;
import com.vish.account.dto.AccountDTO;
import com.vish.account.dto.CustomerDTO;
import com.vish.account.entity.Accounts;
import com.vish.account.entity.Customer;
import com.vish.account.exception.CustomerAlreadyExistException;
import com.vish.account.exception.ResourceNotFoundException;
import com.vish.account.mapper.AccountMapper;
import com.vish.account.mapper.CustomerMapper;
import com.vish.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer= CustomerMapper.mapToCustomer(customerDTO,new Customer());
        Optional<Customer>optionalCustomer=customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already registered with given mobile Number "+customerDTO.getMobileNumber());
        }
        Customer savedCustomer=customerRepository.save(customer);
        Accounts account=createNewAccount(savedCustomer);
        accountRepository.save(account);
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).
                orElseThrow(
                        ()->new ResourceNotFoundException("customer","mobileNumber",mobileNumber)
                );
        Accounts accounts=accountRepository.findBycustomerId(customer.getCustomerId()).
                orElseThrow(
                        ()->new ResourceNotFoundException("account","customerId",String.valueOf(customer.getCustomerId()))
                );
        CustomerDTO customerDTO=CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
        customerDTO.setAccountDTO( AccountMapper.mapToAccountDTO(accounts,new AccountDTO()));
        return  customerDTO;
       }
/*
  Using accountNumber present in accountDTO we are finding accounts
  We are using mapper to update account value from accountDTO and saving it
  Using customerId present in account we are finding Customer
  Using mapper to update Customer value from customerDTO and saving it.
 */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated=false;
        AccountDTO accountDTO=customerDTO.getAccountDTO();
        if(accountDTO!=null){
            Accounts accounts=accountRepository.findById(accountDTO.getAccountNumber()).
                    orElseThrow(()->new ResourceNotFoundException("Account","AccountNumber",String.valueOf(accountDTO.getAccountNumber())));
            AccountMapper.mapToAccount(accountDTO,accounts);
            accounts=accountRepository.save(accounts);

            Long customerId=accounts.getCustomerId();
            Customer customer=customerRepository.findById(customerId).
                    orElseThrow(
                            ()->new ResourceNotFoundException("customer","customerID",customerId.toString())

                    );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated=true;


        }
        return  isUpdated;
    }
/*
Based on Mobile Number it will load customer Entity detail from db
Using customer Id we will delete both account and customer as customerId is common in both of them.
 */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).
                orElseThrow(
                        ()->new ResourceNotFoundException("Customer","MobileNumber",mobileNumber.toString())
                );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /*
    This is private method which is used to create newAccount
    It will accept customer entity and populating account
    It is simple private method which accepts customer object and return account object.
     */
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber=100000000+new Random().nextInt(9000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVING);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        System.out.println(newAccount);
        return newAccount;
    }
}
