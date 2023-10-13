package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.ISizeRepo;
import com.example.module4_shoesshop.Service.IService.ISizeService;
import com.example.module4_shoesshop.model.Product;
import com.example.module4_shoesshop.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizeService implements ISizeService {
    @Autowired
    ISizeRepo iSizeRepo;
    @Override
    public Size save(Size size) {
        return iSizeRepo.save(size);
    }

    @Override
    public Size edit(Size size) {
        return iSizeRepo.save(size);
    }

    @Override
    public void delete(long id) {
        iSizeRepo.deleteById(id);
    }

    @Override
    public Size findById(long id) {
        return iSizeRepo.findById(id).get();
    }

    @Override
    public List<Size> getAll() {
        return iSizeRepo.findAll();
    }

    @Override
    public List<Size> findSizesByProductId(long id) {
        return iSizeRepo.findSizesByProductId(id);
    }
}
