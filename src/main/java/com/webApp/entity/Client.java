package com.webApp.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@Table(name = "client")
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "last_name", nullable = true, length = -1)
    private String lastName;
    @Basic
    @Column(name = "first_name", nullable = true, length = -1)
    private String firstName;
    @Basic
    @Column(name = "password", nullable = true, length = -1)
    private String password;
    @Basic
    @Column(name = "date_of_registration", nullable = true)
    private LocalDate dateOfRegistration;
    @Basic
    @Column(name = "phone_number", nullable = true, length = -1)
    private String phoneNumber;
    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;
    @OneToMany(mappedBy = "clientByUserId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Code> codesById;
    @OneToMany(mappedBy = "clientByClientId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<DebitCard> debitCardsById;
    @OneToMany(mappedBy = "clientByClientId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Loan> loansById;

}