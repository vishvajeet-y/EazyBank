package com.vish.account.mapper;

import com.vish.account.dto.CustomerDTO;
import com.vish.account.entity.Customer;
/*
In order to save the database into the database with the help of repository interface ,we need to send
the object of Customer entity but not the CustomerDTO class
This would convert DTO to entity class and entity class to DTO.
 */
public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer,CustomerDTO customerDTO){
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
         return  customerDTO;
    }

    public static  Customer mapToCustomer(CustomerDTO customerDTO,Customer customer){
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());

        return  customer;
    }


}
