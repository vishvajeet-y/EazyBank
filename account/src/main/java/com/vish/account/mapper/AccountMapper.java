package com.vish.account.mapper;

import com.vish.account.dto.AccountDTO;
import com.vish.account.entity.Accounts;

//This mapper is going to take care of mapping DTO to entity and
//entity to DTO
public class AccountMapper {

    //It is mapping data from Account to AccountDTO
public static AccountDTO mapToAccountDTO(Accounts accounts,AccountDTO accountDTO){
    accountDTO.setAccountNumber(accounts.getAccountNumber());
    accountDTO.setAccountType(accounts.getAccountType());
    accountDTO.setBranchAddress(accounts.getBranchAddress());
    return accountDTO;
}

//It transfer AccountDTO to entity class

    public static Accounts mapToAccount(AccountDTO accountDTO,Accounts accounts){

    accounts.setAccountNumber(accountDTO.getAccountNumber());
    accounts.setAccountType(accountDTO.getAccountType());
    accounts.setBranchAddress(accountDTO.getBranchAddress());

    return  accounts;
    }


}
