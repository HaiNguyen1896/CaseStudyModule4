package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IOderRepo;
import com.example.module4_shoesshop.Service.IService.IOderService;
import com.example.module4_shoesshop.model.Oder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OderService implements IOderService {
    @Autowired
    IOderRepo iOderRepo;
    @Override
    public Oder save(Oder oder) {
        return iOderRepo.save(oder);
    }

    @Override
    public Oder edit(Oder oder) {
        return iOderRepo.save(oder);
    }

    @Override
    public void delete(long id) {
        iOderRepo.deleteById(id);
    }

    @Override
    public Oder findById(long id) {
        return iOderRepo.findById(id).get();
    }

    @Override
    public List<Oder> getAll() {
        return iOderRepo.findAll();
    }
}
