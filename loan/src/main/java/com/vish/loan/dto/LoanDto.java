package com.vish.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Schema(name = "Loans",
        description = "Schema to hold Loan information"
)
@Data//it generate several method ie:getter,setter,equals(),hashCode() and toString()
public class LoanDto {
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @Schema(
            description = "Mobile Number of Customer", example = "4365327698"
    )
    private String mobileNumber;
    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @Schema(
            description = "Loan Number of the customer", example = "548732457654"
    )
    private String loanNumber;
    @NotEmpty(message = "LoanType can not be a null or empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
    private String loanType;
    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Total loan amount", example = "100000"
    )
    private int totalLoan;
    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
    private int ammountPaid;
    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
    private int outStandingAmmount;
}
/*
---DTO (Data Transfer Object) is a simple class used to transfer data between layers (like Controller → Service → Client)
---It usually contains only fields + getters/setters, no business logic.
---Beneifit
| #   | Benefit                      | Explanation                                                                                  |
| --- | ---------------------------- | -------------------------------------------------------------------------------------------- |
| 1️⃣ | **Data Security / Privacy**  | Prevents exposing sensitive data (like passwords or internal IDs)                            |
| 2️⃣ | **Decoupling Layers**        | Your API model (DTO) can change **independently** of your database model (Entity)            |
| 3️⃣ | **Simpler API Responses**    | You can combine or shape data for frontend needs (e.g., join multiple entities into one DTO) |
| 4️⃣ | **Validation Control**       | Use `@Valid` and `@NotNull` annotations in DTOs to validate incoming requests                |
| 5️⃣ | **Performance Optimization** | Send only required fields, reducing payload size                                             |
| 6️⃣ | **Maintainability**          | If your DB schema changes, your external API doesn’t break — just adjust DTO mappings        |
| 7️⃣ | **Readable Contracts**       | DTOs clearly define what data goes *in* and *out* of your API                                |

Diffrence between Entity and DTO.
| Aspect         | Entity                             | DTO                            |
| -------------- | ---------------------------------- | ------------------------------ |
| Purpose        | Represents database table          | Represents API input/output    |
| Contains       | All fields including internal ones | Only required fields           |
| Used In        | Repository & persistence           | Controller & API communication |
| Mapping Needed | No                                 | Yes (Entity ↔ DTO)             |


 */