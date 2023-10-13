package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepo extends JpaRepository<Category,Long> {
}
