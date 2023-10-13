package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CustomerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullname;
    private String address;
    private String country;
    private String email;
    private String phoneNumber;
    private String orderNotes;
    @OneToOne
    private Oder oder;
    @OneToOne
    private Account account;
}
