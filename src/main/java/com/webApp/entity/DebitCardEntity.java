package com.webApp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "debitCard", schema = "public", catalog = "bank_db")
public class DebitCardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "debitCardId", nullable = false)
    private long debitCardId;
    @Basic
    @Column(name = "currentBalance", nullable = true, precision = 0)
    private Double currentBalance;
    @Basic
    @Column(name = "cardNumber", nullable = true, length = 25)
    private String cardNumber;
    @Basic
    @Column(name = "cvv", nullable = true)
    private Integer cvv;
    @Basic
    @Column(name = "firstName", nullable = true, length = 25)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = true, length = 25)
    private String lastName;
    @Basic
    @Column(name = "term", nullable = true)
    private Date term;
    @Basic
    @Column(name = "userId", nullable = true)
    private Long userId;
    @OneToMany(mappedBy = "debitCardByDebitCardId")
    private Collection<ClientEntity> clientsByDebitCardId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "clientId",insertable = false, updatable = false)
    private ClientEntity clientByUserId;

    public long getDebitCardId() {
        return debitCardId;
    }

    public void setDebitCardId(long debitCardId) {
        this.debitCardId = debitCardId;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
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
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<ClientEntity> getClientsByDebitCardId() {
        return clientsByDebitCardId;
    }

    public void setClientsByDebitCardId(Collection<ClientEntity> clientsByDebitCardId) {
        this.clientsByDebitCardId = clientsByDebitCardId;
    }

    public ClientEntity getClientByUserId() {
        return clientByUserId;
    }

    public void setClientByUserId(ClientEntity clientByUserId) {
        this.clientByUserId = clientByUserId;
    }
}
