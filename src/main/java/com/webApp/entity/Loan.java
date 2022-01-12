package com.webApp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Loan {

    private LocalDate dateOfRegistration;
    private double amount;
    private double interestRate;
    private int creditTerm;
    private double monthlyPayment;
    private User userId;


    public Loan() {
    }

    public Loan(LocalDate dateOfRegistration, double amount, double interestRate, int creditTerm, double monthlyPayment, User userId) {
        this.dateOfRegistration = dateOfRegistration;
        this.amount = amount;
        this.interestRate = interestRate;
        this.creditTerm = creditTerm;
        this.monthlyPayment = monthlyPayment;
        this.userId = userId;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(int creditTerm) {
        this.creditTerm = creditTerm;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
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
        Loan loan = (Loan) o;
        return Double.compare(loan.amount, amount) == 0 && Double.compare(loan.interestRate, interestRate) == 0 && creditTerm == loan.creditTerm && Double.compare(loan.monthlyPayment, monthlyPayment) == 0 && Objects.equals(dateOfRegistration, loan.dateOfRegistration) && Objects.equals(userId, loan.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfRegistration, amount, interestRate, creditTerm, monthlyPayment, userId);
    }
}
