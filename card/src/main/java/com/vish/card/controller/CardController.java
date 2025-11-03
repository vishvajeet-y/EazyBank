package com.vish.card.controller;

import com.vish.card.constants.cardConstants;
import com.vish.card.dto.CardDto;
import com.vish.card.dto.ErrorResponseDto;
import com.vish.card.dto.ResponseDto;
import com.vish.card.services.ICardServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
public class CardController {
    ICardServices cardServices;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber){

        cardServices.create(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(cardConstants.STATUS_201,cardConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardDto>fetchCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                String mobileNumber){
        CardDto cardDto=cardServices.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(cardDto);
    }
    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto>updateCard(@Valid @RequestBody CardDto cardDto){
         boolean isUpdated=cardServices.updateCard(cardDto);
         if(isUpdated){
             return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(cardConstants.STATUS_200,cardConstants.MESSAGE_200));
         }
         else {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(cardConstants.STATUS_417,cardConstants.MESSAGE_417_UPDATE));
         }
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
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
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                     String mobileNumber){
        boolean isDeleted=cardServices.deleteCard(mobileNumber);
        if(isDeleted){
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(cardConstants.STATUS_200,cardConstants.MESSAGE_200));
        }
        else {
            System.out.println("delete controller");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(cardConstants.STATUS_417,cardConstants.MESSAGE_417_DELETE));
        }
    }

}
