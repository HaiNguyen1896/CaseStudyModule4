package com.example.module4_shoesshop.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "product_name",unique = true)
    private String productName;
    @ManyToMany
    private List<Size> size;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "sale_percentage",columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double salePercentage;
    @Column(name = "sale_price")
    private Double salePrice;
    @Column(name = "quantity",nullable = false)
    private int quantity;
    @Column(name = "color",nullable = false)
    private String color;
    @Column(name = "describle",columnDefinition = "TEXT")
    private String describle;
    @Column(name = "status",columnDefinition = "BIT(1) default b'0'")
    private boolean status;
    @Column(name="image",columnDefinition = "TEXT")
    private String image;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Category category;

}
