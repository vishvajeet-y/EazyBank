package com.vish.card.repository;

import com.vish.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICardRepository extends JpaRepository<Card,Long> {

    Optional<Card> findByMobileNumber(String  mobileNumber);
    Optional<Card>findByCardNumber(String cardNumber);

}
