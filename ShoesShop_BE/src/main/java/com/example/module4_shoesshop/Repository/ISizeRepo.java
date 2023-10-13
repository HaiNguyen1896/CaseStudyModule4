package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Product;
import com.example.module4_shoesshop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISizeRepo extends JpaRepository<Size,Long> {
    @Query(nativeQuery = true,value = "select s.size from size s left join product_size ps on s.id = ps.size_id where ps.product_id=:id")
    List<Size> findSizesByProductId(@Param("id") long id);
}
