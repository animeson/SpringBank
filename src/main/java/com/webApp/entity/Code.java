package com.webApp.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "code")
public class Code {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "code", nullable = true, length = -1)
    private String code;
    @Basic
    @Column(name = "expiration_date", nullable = true)
    private LocalDate expirationDate;
    @Basic
    @Column(name = "client_id", nullable = true)
    private Long userId;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "client_id", referencedColumnName = "id",updatable = false, insertable = false)
    private Client clientByUserId;


}
