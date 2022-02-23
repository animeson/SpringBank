package com.webApp.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Data
public class Loan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "loan_date", nullable = true)
    private LocalDate loanDate;
    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    private Double amount;
    @Basic
    @Column(name = "interest_rate", nullable = true, precision = 0)
    private Double interestRate;
    @Basic
    @Column(name = "credit_term", nullable = true)
    private Integer creditTerm;
    @Basic
    @Column(name = "monthly_payment", nullable = true, precision = 0)
    private Double monthlyPayment;
    @Basic
    @Column(name = "description", nullable = true, length = 50)
    private String description;
    @Basic
    @Column(name = "client_id", nullable = true)
    private Long clientId;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "client_id", referencedColumnName = "id",updatable = false, insertable = false)
    private Client clientByClientId;
}