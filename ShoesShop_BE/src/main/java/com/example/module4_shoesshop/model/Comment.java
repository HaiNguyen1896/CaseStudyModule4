package com.example.module4_shoesshop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate commentTime;
    private String comment;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Product product;
}
