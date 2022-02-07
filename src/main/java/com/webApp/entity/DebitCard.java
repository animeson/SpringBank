package com.webApp.entity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DebitCard {
    private double currentBalance;
    private String cardNumber;
    private int CVV;
    private String firstName;
    private String lastName;
    private LocalDate term;
    private Long userId;
}