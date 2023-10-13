package com.example.module4_shoesshop.model.dto;

import com.example.module4_shoesshop.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class OrderDataDTO {
    private Product[] products;
    private CustomerInfoDTO customerInfoDTO;
    List<OrderDataDTO> orderDataDTOList;
}
