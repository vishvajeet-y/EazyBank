package com.vish.card.exception;

public class CardAlreadyExistException extends RuntimeException{

      public CardAlreadyExistException(String message){
          super(message);
      }
}
