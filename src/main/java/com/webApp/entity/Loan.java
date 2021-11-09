package com.webApp.entity;

import java.util.Date;
import java.util.Objects;

public class Loan {

    private Date dateOfRegistration;
    private double amount;
    private double interestRate;
    private int creditTerm;
    private double monthlyPayment;


    public Loan() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Double.compare(loan.amount, amount) == 0 && Double.compare(loan.interestRate, interestRate) == 0 && creditTerm == loan.creditTerm && Double.compare(loan.monthlyPayment, monthlyPayment) == 0 && dateOfRegistration.equals(loan.dateOfRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfRegistration, amount, interestRate, creditTerm, monthlyPayment);
    }

    @Override
    public String toString() {
        return "ru.svistun.springcourse.entity.Loan{" +
                "dateOfRegistration=" + dateOfRegistration +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", creditTerm=" + creditTerm +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
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

    public Loan(Date dateOfRegistration, double amount, double interestRate, int creditTerm, double monthlyPayment) {
        this.dateOfRegistration = dateOfRegistration;
        this.amount = amount;
        this.interestRate = interestRate;
        this.creditTerm = creditTerm;
        this.monthlyPayment = monthlyPayment;
    }
}
