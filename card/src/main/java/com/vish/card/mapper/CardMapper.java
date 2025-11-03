package com.vish.card.mapper;

import com.vish.card.dto.CardDto;
import com.vish.card.entity.Card;

public class CardMapper {

    public static CardDto mapToCardDto(Card card, CardDto cardDto){

        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setAmmountUsed(card.getAmmountUsed());
        cardDto.setAvailableAmmount(card.getAvailableAmmount());
        return cardDto;
    }
    public static Card mapToCard(CardDto cardDto,Card card){
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setAmmountUsed(cardDto.getAmmountUsed());
        card.setAvailableAmmount(card.getAvailableAmmount());
        return  card;
    }

}
