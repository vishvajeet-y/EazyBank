package com.vish.loan.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix= "loans")
@Getter
@Setter
public class LoanContactInfoDto {
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
@ConfigurationProperties(prefix= "loans")
public record LoanContactInfoDto(String message, Map<String,String>contactDetails, List<String> onCallSupport) {

}
*/
