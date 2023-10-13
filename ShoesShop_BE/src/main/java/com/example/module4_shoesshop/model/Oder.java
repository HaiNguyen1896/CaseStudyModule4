package com.example.module4_shoesshop.model;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "oder")
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "quantity",nullable = false)
    private int quantity;
    @Column(name = "total_price",nullable = false)
    private double totalPrice;
    @Column(name = "date",nullable = false)
    private LocalDateTime date;
    @ManyToOne
    private Account account;
}
