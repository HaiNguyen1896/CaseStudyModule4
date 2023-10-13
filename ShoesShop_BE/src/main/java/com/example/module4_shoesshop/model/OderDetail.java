package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "oderdetail")
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "quantity",nullable = false)
    private int quantity;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "size",nullable = false)
    private int size;
    @ManyToOne
    Product product;
    @ManyToOne
    Oder oder;
}
