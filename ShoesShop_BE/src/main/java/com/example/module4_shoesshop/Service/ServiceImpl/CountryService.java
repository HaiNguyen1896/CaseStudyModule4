package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.ICountryRepo;
import com.example.module4_shoesshop.Service.IService.ICountryService;
import com.example.module4_shoesshop.model.Country;
import com.example.module4_shoesshop.Service.IService.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService implements ICountryService {
    @Autowired
    ICountryRepo iCountryRepo;
    @Override
    public Country save(Country country) {
        return iCountryRepo.save(country);
    }

    @Override
    public Country edit(Country country) {
        return iCountryRepo.save(country);
    }

    @Override
    public void delete(long id) {
        iCountryRepo.deleteById(id);
    }

    @Override
    public Country findById(long id) {
        return iCountryRepo.findById(id).get();
    }
    @Override
    public List<Country> getAll() {
        return iCountryRepo.findAll();
    }
}
