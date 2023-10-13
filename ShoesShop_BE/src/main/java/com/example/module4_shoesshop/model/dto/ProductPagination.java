package com.example.module4_shoesshop.model.dto;

import com.example.module4_shoesshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPagination {
    private int totalPages;
    private double productQuantity;
    private List<Product> ListPaginationProducts;
}
