package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IBrandRepo;
import com.example.module4_shoesshop.Service.IService.IBrandService;
import com.example.module4_shoesshop.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService implements IBrandService {
    @Autowired
    IBrandRepo iBrandRepo;
    @Override
    public Brand save(Brand brand) {
        return iBrandRepo.save(brand);
    }

    @Override
    public Brand edit(Brand brand) {
        return iBrandRepo.save(brand);
    }

    @Override
    public void delete(long id) {
        iBrandRepo.deleteById(id);
    }

    @Override
    public Brand findById(long id) {
        return iBrandRepo.findById(id).get();
    }

    @Override
    public List<Brand> getAll() {
        return iBrandRepo.findAll();
    }
}
