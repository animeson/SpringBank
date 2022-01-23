package com.webApp.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private LocalDate loanDate; // когда взял кредит
    private double amount; // сумма
    private double interestRate; // процентная ставка
    private int creditTerm; // срок
    private double monthlyPayment; //ежемесячная оплата
    private Long userId; // id пользователя
    private String description; // описание кредита / для чего кредит



    public Loan() {
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Double.compare(loan.amount, amount) == 0 && Double.compare(loan.interestRate, interestRate) == 0 && creditTerm == loan.creditTerm && Double.compare(loan.monthlyPayment, monthlyPayment) == 0 && Objects.equals(loanDate, loan.loanDate) && Objects.equals(userId, loan.userId) && Objects.equals(description, loan.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanDate, amount, interestRate, creditTerm, monthlyPayment, userId, description);
    }
}
