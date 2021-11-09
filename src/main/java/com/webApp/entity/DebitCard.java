package com.webApp.entity;

import java.util.Date;
import java.util.Objects;

public class DebitCard  {
    private double currentBalance;
    private String cardNumber;
    private int CVV;
    private String firstName;
    private String lastName;
    private Date Term;


    public DebitCard() {}


    public DebitCard(double currentBalance, String cardNumber, int CVV, String firstName, String lastName, Date term) {
        this.currentBalance = currentBalance;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.firstName = firstName;
        this.lastName = lastName;
        Term = term;
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

    public Date getTerm() {
        return Term;
    }

    public void setTerm(Date term) {
        Term = term;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebitCard debitCard = (DebitCard) o;
        return Double.compare(debitCard.currentBalance, currentBalance) == 0 && CVV == debitCard.CVV && Objects.equals(cardNumber, debitCard.cardNumber) && Objects.equals(firstName, debitCard.firstName) && Objects.equals(lastName, debitCard.lastName) && Objects.equals(Term, debitCard.Term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentBalance, cardNumber, CVV, firstName, lastName, Term);
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "currentBalance=" + currentBalance +
                ", cardNumber='" + cardNumber + '\'' +
                ", CVV=" + CVV +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Term=" + Term +
                '}';
    }
}
