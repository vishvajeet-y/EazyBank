package com.vish.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
    Whenever I'm throwing an exception in response , my client is going to
    receive a status of BAD_REQUEST.
     */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistException extends  RuntimeException{



       public CustomerAlreadyExistException(String message){
             super(message);
      }

}
