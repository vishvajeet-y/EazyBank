package com.vish.account.controller;


import com.vish.account.AccountApplication;
import com.vish.account.constants.AccountConstants;
import com.vish.account.dto.CustomerDTO;
import com.vish.account.dto.ErrorResponseDTO;
import com.vish.account.dto.ResponseDTO;
import com.vish.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.awt.*;
@Tag(
        name = "CRUD REST API For Account in Eazy Banks",
        description = "CRUD REST API in Eazy Banks to CREATE,UPDATE,FETCH and DELETE AccountDetails"
)
@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})//it tells REST API are going to response type of JSON
@AllArgsConstructor
@Validated  //validate  data  we are sending as input in dto
public class AccountController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST APIs to create new Customer & Account inside EazyBanks"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO>createAccount(@Valid @RequestBody CustomerDTO customerDTO){

        iAccountService.createAccount(customerDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO>fetchAccountDetails(@RequestParam
                                             @Pattern(regexp = "(^[0-9]{10}$)") String mobileNumber){
        CustomerDTO customerDTO=iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST APIs to update  Customer & Account details based on account Numbers"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
   @PutMapping("/update")
   public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO){
        System.out.println("customerDTO "+customerDTO);
         boolean isUpdated=iAccountService.updateAccount(customerDTO);
         if(isUpdated){
             return ResponseEntity.status(HttpStatus.OK)
                     .body(new ResponseDTO(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
         }
         else {
             return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                     body(new ResponseDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
         }
   }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )}
    )
   @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^[0-9]{10}$)") String mobileNumber){
        boolean isDeleted=iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return  ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDTO(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body(new ResponseDTO(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_DELETE));

        }
   }

}
