package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface IProductRepo extends PagingAndSortingRepository<Product,Long> {
    @Query(nativeQuery = true,value = "select count(*) from product inner join category on product.category_id = category.id where category.id=:id")
    int findByCateID (@Param("id") long id);
    @Query(nativeQuery = true,value = "select * from product p where p.price between :r1 and :r2")
    List<Product> findProductByPriceRange (@Param("r1") int r1,@Param("r2") int r2);

    @Query(nativeQuery = true,value = "select product.* from size inner join product_size on size.id=product_size.size_id inner join product on product_size.product_id = product.id where size.id=:id")
    List<Product> findProductBySizeID(@Param("id") long id);
    @Query(value = "select p from Product p inner join Category c on p.category.id = c.id where c.id=:id")
    List<Product> findProductCategoryID(@Param("id") long id);

}
