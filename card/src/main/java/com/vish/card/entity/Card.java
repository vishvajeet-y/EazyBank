package com.vish.card.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "total_limit")
    private Long totalLimit;
    @Column(name = "ammount_used")
    private Long ammountUsed;
    @Column(name = "available_ammount")
    private Long availableAmmount;

}
