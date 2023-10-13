package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepo extends JpaRepository<Country,Long> {
}
