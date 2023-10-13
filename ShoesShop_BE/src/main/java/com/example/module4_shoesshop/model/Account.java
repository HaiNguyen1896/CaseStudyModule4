package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="verification_code",updatable = false)
    private String verificationCode;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isEnable;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Role role;
}
