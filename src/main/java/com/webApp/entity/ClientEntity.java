package com.webApp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "client", schema = "public", catalog = "bank_db")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "clientId", nullable = false)
    private long clientId;
    @Basic
    @Column(name = "lastName", nullable = true, length = 25)
    private String lastName;
    @Basic
    @Column(name = "firstName", nullable = true, length = 25)
    private String firstName;
    @Basic
    @Column(name = "password", nullable = true, length = 50)
    private String password;
    @Basic
    @Column(name = "dateOfRegistration", nullable = true)
    private Date dateOfRegistration;
    @Basic
    @Column(name = "phoneNumber", nullable = true)
    private Integer phoneNumber;
    @Basic
    @Column(name = "email", nullable = true, length = 25)
    private String email;
    @Basic
    @Column(name = "loanId", nullable = true,insertable = false, updatable = false)
    private Long loanId;
    @Basic
    @Column(name = "debitCardId", nullable = true, insertable = false, updatable = false)
    private Long debitCardId;
    @ManyToOne
    @JoinColumn(name = "loanId", referencedColumnName = "loanId")
    private LoanEntity loanByLoanId;
    @ManyToOne
    @JoinColumn(name = "debitCardId", referencedColumnName = "debitCardId")
    private DebitCardEntity debitCardByDebitCardId;
    @OneToMany(mappedBy = "clientByUserId")
    private Collection<DebitCardEntity> debitCardsByClientId;
    @OneToMany(mappedBy = "clientByUserId")
    private Collection<LoanEntity> loansByClientId;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDebitCardId() {
        return debitCardId;
    }

    public void setDebitCardId(Long debitCardId) {
        this.debitCardId = debitCardId;
    }

    public LoanEntity getLoanByLoanId() {
        return loanByLoanId;
    }

    public void setLoanByLoanId(LoanEntity loanByLoanId) {
        this.loanByLoanId = loanByLoanId;
    }

    public DebitCardEntity getDebitCardByDebitCardId() {
        return debitCardByDebitCardId;
    }

    public void setDebitCardByDebitCardId(DebitCardEntity debitCardByDebitCardId) {
        this.debitCardByDebitCardId = debitCardByDebitCardId;
    }

    public Collection<DebitCardEntity> getDebitCardsByClientId() {
        return debitCardsByClientId;
    }

    public void setDebitCardsByClientId(Collection<DebitCardEntity> debitCardsByClientId) {
        this.debitCardsByClientId = debitCardsByClientId;
    }

    public Collection<LoanEntity> getLoansByClientId() {
        return loansByClientId;
    }

    public void setLoansByClientId(Collection<LoanEntity> loansByClientId) {
        this.loansByClientId = loansByClientId;
    }
}
