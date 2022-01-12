package com.webApp.entity;
import java.time.LocalDate;
import java.util.Objects;

public class DebitCard {
    private double currentBalance;
    private String cardNumber;
    private int CVV;
    private String firstName;
    private String lastName;
    private LocalDate term;
    private User userId;


    public DebitCard() {
    }

    public DebitCard(double currentBalance, String cardNumber, int CVV, String firstName, String lastName, LocalDate term, User userId) {
        this.currentBalance = currentBalance;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.firstName = firstName;
        this.lastName = lastName;
        this.term = term;
        this.userId = userId;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getTerm() {
        return term;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCard debitCard = (DebitCard) o;
        return Double.compare(debitCard.currentBalance, currentBalance) == 0 && CVV == debitCard.CVV && Objects.equals(cardNumber, debitCard.cardNumber) && Objects.equals(firstName, debitCard.firstName) && Objects.equals(lastName, debitCard.lastName) && Objects.equals(term, debitCard.term) && Objects.equals(userId, debitCard.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentBalance, cardNumber, CVV, firstName, lastName, term, userId);
    }
}