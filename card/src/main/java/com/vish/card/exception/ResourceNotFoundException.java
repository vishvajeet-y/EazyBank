package com.vish.card.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String ResourceName,String fieldName,String fieldValue){
        super(String.format("%s is not found for given, input %s,%s",ResourceName,fieldName,fieldValue));
    }
}
