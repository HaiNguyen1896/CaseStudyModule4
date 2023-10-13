package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepo extends JpaRepository<Images,Long> {

}
