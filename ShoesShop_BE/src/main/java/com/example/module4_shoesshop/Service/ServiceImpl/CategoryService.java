package com.example.module4_shoesshop.Service.ServiceImpl;



import com.example.module4_shoesshop.Repository.ICategoryRepo;
import com.example.module4_shoesshop.Service.IService.ICategoryService;
import com.example.module4_shoesshop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepo iCategoryRepo;
    @Override
    public Category save(Category category) {
        return iCategoryRepo.save(category);
    }

    @Override
    public Category edit(Category category) {
        return iCategoryRepo.save(category);
    }

    @Override
    public void delete(long id) {
        iCategoryRepo.deleteById(id);
    }

    @Override
    public Category findById(long id) {
        return iCategoryRepo.findById(id).get();
    }

    @Override
    public List<Category> getAll() {
        return iCategoryRepo.findAll();
    }
}
