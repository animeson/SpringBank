package com.webApp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "loan", schema = "public", catalog = "bank_db")
public class LoanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "loanId", nullable = false)
    private long loanId;
    @Basic
    @Column(name = "loanDate", nullable = true)
    private Date loanDate;
    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    private Double amount;
    @Basic
    @Column(name = "interestRate", nullable = true, precision = 0)
    private Double interestRate;
    @Basic
    @Column(name = "creditTerm", nullable = true)
    private Integer creditTerm;
    @Basic
    @Column(name = "monthlyPayment", nullable = true, precision = 0)
    private Double monthlyPayment;
    @Basic
    @Column(name = "description", nullable = true, length = 50)
    private String description;
    @Basic
    @Column(name = "userId", nullable = true)
    private Long userId;
    @OneToMany(mappedBy = "loanByLoanId")
    private Collection<ClientEntity> clientsByLoanId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "clientId",insertable = false, updatable = false)
    private ClientEntity clientByUserId;

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(Integer creditTerm) {
        this.creditTerm = creditTerm;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<ClientEntity> getClientsByLoanId() {
        return clientsByLoanId;
    }

    public void setClientsByLoanId(Collection<ClientEntity> clientsByLoanId) {
        this.clientsByLoanId = clientsByLoanId;
    }

    public ClientEntity getClientByUserId() {
        return clientByUserId;
    }

    public void setClientByUserId(ClientEntity clientByUserId) {
        this.clientByUserId = clientByUserId;
    }
}
