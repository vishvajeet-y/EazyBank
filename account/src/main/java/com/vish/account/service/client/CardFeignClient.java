package com.vish.account.service.client;

import com.vish.account.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardFeignClient {

    @GetMapping(value = "/api/fetch",consumes = "application/json")
    public ResponseEntity<CardDto>fetchCardDetails(@RequestParam String mobileNumber);
}
/*
   ---> To use Feign libraries, we're using FeignClient, we're using "cards/loans" , this is same value which we've used to
         register with Eurekaserver..  We've mentioned "cards",so feign client will connect with Eurekaserver at runtime and
         it will try to get all instance details with logical name "cards"
  ---> Abstract method ,this method signature shall match with actual REST APIs method that we've defined inside card MS ,
       we will remove @Validation because it will be performed at validation.
  ---> We can choose whatever name inside Feign Client interface but make sure method signature like input parameter,return parameter
       along with method access type should remain same as what we've defined inside actual MS..
  --->On top of method ,we need to mention REST API path details that my actual method inside CardController is going to support
       consumes-->we're trying to communicate with Feign Client that API which we've inside MS ,it is going to accept JSON input data

 --->Behind Scene,my cardFeignClient will connect with EurekaServer and try to fetch all the instances that are registered with
     logical name cards.Once it receive 1 or 2 any other instance details , it will try to cache these details for 30s which is defual
     period,it is not going to connect with Eureka Server ,but instead it is going to leverage details present inside cache..
     So Based upon IP Details inside cache, it is going to invoke this API along with request with Mobile Number
 */
