package com.example.module4_shoesshop.Repository;

import com.example.module4_shoesshop.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerInfoRepo extends JpaRepository<CustomerInfo,Long> {
}
