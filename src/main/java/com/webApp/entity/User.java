package com.webApp.entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class User {
    long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private String phoneNumber;
    private List<DebitCard> card;
    private List<Loan> loan;

    public User() {
    }

    public User(long id, String firstName, String lastName, String email, String password, LocalDate registrationDate, String phoneNumber, List<DebitCard> card, List<Loan> loan) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.card = card;
        this.loan = loan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DebitCard> getCard() {
        return card;
    }

    public void setCard(List<DebitCard> card) {
        this.card = card;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(card, user.card) && Objects.equals(loan, user.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, registrationDate, phoneNumber, card, loan);
    }
}