package com.vish.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfoDTO {
    private  String message;
    private Map<String,String>contactDetails;
    private  List<String>onCallSupport;
}
/*
Previously Record class was holding properties value and we cannot change value of these properties
by invoking setter method as all our field are going to be final.. Once value of our field are going to
be created using constructor there is no way to change value inside these fields..
 */

/*
Record -->It is new type of Java class .Sometime we want our POJO or DTO class to simply act as data carriers.. which means I will create
object of DTO class and someone can read the data from the object of DTO class but they shall not be able to change it.
So whatever value we pass during Object creation the same value are  final and anyone can read using getter method but it doesn't
have setter method

@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDTO(String message, Map<String,String> contactDetails, List<String>onCallSupport) {
}

Behind the scene Java is going to make field final and at something it is going to generate getter method and constructor behind scene
There won't be any setter method which means we can only intialize the data only once and we cannot change that and whatever we provide
during object creation ,it is going to be final
To map all the properties to Java Fields ,we need to mention the prefix with help of @ConfigurationProperties
 */
