package com.vish.account.service;

import com.vish.account.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO  customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
