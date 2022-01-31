package com.webApp.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class Loan {
    private LocalDate loanDate; // когда взял кредит
    private double amount; // сумма
    private double interestRate; // процентная ставка
    private int creditTerm; // срок
    private double monthlyPayment; //ежемесячная оплата
    private Long userId; // id пользователя
    private String description; // описание кредита / для чего кредит

}
