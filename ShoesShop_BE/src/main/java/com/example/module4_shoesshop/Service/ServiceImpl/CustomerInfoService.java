package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.ICustomerInfoRepo;
import com.example.module4_shoesshop.Service.IService.ICustomerInfoService;
import com.example.module4_shoesshop.model.CustomerInfo;
import com.example.module4_shoesshop.model.dto.CustomerInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInfoService implements ICustomerInfoService {
    @Autowired
    ICustomerInfoRepo iCustomerInfoRepo;

    @Override
    public CustomerInfo save(CustomerInfo customerInfo) {
        return iCustomerInfoRepo.save(customerInfo);
    }

    @Override
    public CustomerInfo edit(CustomerInfo customerInfo) {
        return iCustomerInfoRepo.save(customerInfo);
    }

    @Override
    public void delete(long id) {
        iCustomerInfoRepo.deleteById(id);
    }

    @Override
    public CustomerInfo findById(long id) {
        return iCustomerInfoRepo.findById(id).get();
    }

    @Override
    public List<CustomerInfo> getAll() {
        return iCustomerInfoRepo.findAll();
    }
}
