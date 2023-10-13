package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IImageRepo;
import com.example.module4_shoesshop.Service.IService.IImageService;
import com.example.module4_shoesshop.model.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageService implements IImageService {
    @Autowired
    IImageRepo iImageRepo;
    @Override
    public Images save(Images images) {
        return iImageRepo.save(images);
    }

    @Override
    public Images edit(Images images) {
        return iImageRepo.save(images);
    }

    @Override
    public void delete(long id) {
        iImageRepo.deleteById(id);
    }

    @Override
    public Images findById(long id) {
        return iImageRepo.findById(id).get();
    }
    @Override
    public List<Images> getAll() {
        return iImageRepo.findAll();
    }
}
