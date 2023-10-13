package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "img",unique = true)
    private String img;
    @ManyToOne
    private Product product;
}
