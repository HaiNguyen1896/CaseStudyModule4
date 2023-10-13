package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "country_name",nullable = false,unique = true)
    private String countryName;
}
