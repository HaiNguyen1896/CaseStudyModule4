package com.example.module4_shoesshop.Service.IService;

import com.example.module4_shoesshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService extends IService<Product>{
    int findByCateID (long id);
    List<Product> findProductByPriceRange (@Param("r1") int r1, @Param("r2") int r2);
    List<Product> findProductBySizeID(long id);
    List<Product> findProductByCategoryID(@Param("id") long id);

    List<Product> findProduct (int page, int limit);

}
