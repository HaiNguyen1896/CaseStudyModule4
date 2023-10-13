package com.example.module4_shoesshop.model.dto;

import com.example.module4_shoesshop.model.Product;
import lombok.Data;

@Data
public class CustomerInfoDTO {
    private String fullname;
    private String address;
    private String country;
    private String email;
    private String phoneNumber;
    private String orderNotes;
}
