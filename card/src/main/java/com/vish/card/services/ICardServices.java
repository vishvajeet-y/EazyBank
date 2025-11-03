package com.vish.card.services;

import com.vish.card.dto.CardDto;

public interface ICardServices {
    public void create(String mobileNumber);
    public CardDto fetchCard(String mobileNumber);
    public boolean updateCard(CardDto cardDto);
    public boolean deleteCard(String mobileNumber);

}
