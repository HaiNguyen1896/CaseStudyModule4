package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepo extends JpaRepository<Brand,Long> {
}
