package com.vish.loan.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class Loan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private  int loadId;

    @Column(name="mobilenumber")
    private String mobileNumber;

    @Column(name="loan_number")
    private  String  loanNumber;

    @Column(name = "loan_type`")
    private  String loanType;

    @Column(name = "total_loan")
    private  int totalLoan;

    @Column(name = "ammount_paid")
    private int ammountPaid;

    @Column(name = "Outstading_ammount")
    private int outStandingAmmount;



}
