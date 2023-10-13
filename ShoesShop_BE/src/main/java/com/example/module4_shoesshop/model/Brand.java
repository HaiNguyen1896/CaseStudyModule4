package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "brand_name",nullable = false,unique = true)
    private String brandName;
}
