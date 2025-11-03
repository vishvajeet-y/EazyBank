package com.vish.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@Data generates several method including getter,setter,equals()
  hashCode() and toString()
 */
@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountDTO {
    @Schema(
            description = "Account Number of Eazy Bank account", example = "3454433243"
    )
    @NotEmpty(message = "Account Number cannot be null or empty")
    @Pattern(regexp = "(^$[0-9]{10})",message = "Account number must be 10 digits")
    private Long accountNumber;
    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;
    @Schema(
            description = "Eazy Bank branch address", example = "123 NewYork"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String  branchAddress;



}
