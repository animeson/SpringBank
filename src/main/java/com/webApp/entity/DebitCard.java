package com.webApp.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "debit_card", schema = "public", catalog = "bank_db")
public class DebitCard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "current_balance", nullable = true, precision = 0)
    private Double currentBalance;
    @Basic
    @Column(name = "card_number", nullable = true, length = 25)
    private String cardNumber;
    @Basic
    @Column(name = "cvv", nullable = true)
    private Integer cvv;
    @Basic
    @Column(name = "first_name", nullable = true, length = 25)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = true, length = 25)
    private String lastName;
    @Basic
    @Column(name = "term", nullable = true)
    private LocalDate term;
    @Basic
    @Column(name = "client_id", nullable = true)
    private Long clientId;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "client_id", referencedColumnName = "id",updatable = false, insertable = false)
    private Client clientByClientId;
}
