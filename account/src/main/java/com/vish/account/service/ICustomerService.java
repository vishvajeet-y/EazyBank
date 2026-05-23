package com.vish.account.service;

import com.vish.account.dto.CustomerDetailsDTO;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
