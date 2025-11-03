package com.vish.account.exception;

import com.vish.account.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@ControllerAdvice tells spring whenever an exception happens
in any of controller please invoke method that we are going to
write inside this class
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    This is method inside ResponseEntityExceptionHandler which we are overriding
    We will tell SpringBoot framework what it has to do whenever validation fails
    spring knows validation it has to perform and what message to throw to client
    but it does not know how to send it inside error response
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
    //It return ConstraintViolationResponse inside Controller class when we are using @Pattern()
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation -> {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            errors.put(propertyPath.substring(propertyPath.indexOf(".") + 1), constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Handle CustomerAlreadyExist Exception
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO>handleCustomerAlreadyExistException(
            CustomerAlreadyExistException exception,
            WebRequest webRequest){

        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
                );

         return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
    }


    //handle Resource Not Found Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
           ResourceNotFoundException exception,
           WebRequest webRequest
    ){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.NOT_FOUND);
    }

    /*Handle All Exception
    Exception -> it is class that can represent all kind of Exception including checked and unchecked
    exception
    First Spring boot will look for handler method which are with exact exception details and if there is
    no handler then logic inside above exception handling is going to be invoked

     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO>handleGlobalException(Exception exception,WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()

        );
        return  new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
