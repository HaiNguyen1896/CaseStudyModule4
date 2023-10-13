package com.example.module4_shoesshop.Repository;
import com.example.module4_shoesshop.model.OderDetail;
import com.example.module4_shoesshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOderDetailRepo extends JpaRepository<OderDetail, Long> {
    @Query(value = "select od from OderDetail od where od.oder.id=:id")
    List<OderDetail> findOderDetailByOderID(@Param("id") long id);
}
