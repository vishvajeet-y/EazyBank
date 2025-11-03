package com.vish.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and account Information"
)
public class CustomerDTO {
    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5,max = 30,message = "Length of Customer should be between 5 to 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
    @NotEmpty(message = "Email address cannot be empty")
    @Email(message = "Email address should be valid value")
    private String email;
    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "(^[0-9]{10}$)",message = "mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDTO accountDTO;
}
