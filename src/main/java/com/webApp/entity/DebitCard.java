package com.webApp.entity;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

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