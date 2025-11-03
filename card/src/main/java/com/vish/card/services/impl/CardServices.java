package com.vish.card.services.impl;

import com.vish.card.dto.CardDto;
import com.vish.card.entity.Card;
import com.vish.card.exception.CardAlreadyExistException;
import com.vish.card.exception.ResourceNotFoundException;
import com.vish.card.mapper.CardMapper;
import com.vish.card.repository.ICardRepository;
import com.vish.card.services.ICardServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.vish.card.constants.cardConstants;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServices implements ICardServices {
    ICardRepository cardRepository;

    @Override
    public void create(String mobileNumber) {
        Optional<Card>card=cardRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent())
        {
            throw  new CardAlreadyExistException("Card Already Exist for given mobile number "+mobileNumber);
        }

        cardRepository.save(createCard(mobileNumber));


    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
       Card card=cardRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Card","mobileNumber",mobileNumber));
        CardDto cardDto=CardMapper.mapToCardDto(card,new CardDto());
        return cardDto;
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card=cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(()->new ResourceNotFoundException("Card","cardNumber",cardDto.getCardNumber()));
        CardMapper.mapToCard(cardDto,card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        System.out.println("-----------delete service--------------");
        Card card=cardRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Card","mobileNubmer",mobileNumber));
       System.out.println("deleting services");
        cardRepository.delete(card);
        return true;
    }


    private Card createCard(String mobileNumber){
        Card card=new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setMobileNumber(mobileNumber);
        card.setCardType(cardConstants.CREDIT_CARD);
        card.setTotalLimit((long)(cardConstants.NEW_CARD_LIMIT));
        card.setAmmountUsed(0L);
        card.setAvailableAmmount((long)(cardConstants.NEW_CARD_LIMIT));
        card.setCreatedAt(LocalDateTime.now());
        card.setCreatedBy("Card_CD");
        return card;
    }

}
