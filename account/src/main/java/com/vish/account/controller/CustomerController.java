package com.vish.account.controller;

import com.vish.account.dto.CustomerDetailsDTO;
import com.vish.account.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Tag(
     name = "RESTAPI For Customer in EazyBank",
     description = "RESTAPI in EazyBank to fetch CustomerDetails "
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {
    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @GetMapping("/fetchCustomerDetails")
      public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
                                                                     message = "Mobile Number shall be 10 digits")
                                                                     String mobileNumber){
        CustomerDetailsDTO customerDetailsDTO=iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDTO);
      }

}
