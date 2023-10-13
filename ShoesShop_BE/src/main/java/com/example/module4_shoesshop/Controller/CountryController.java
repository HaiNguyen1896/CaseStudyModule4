package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.ICountryService;
import com.example.module4_shoesshop.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/country")
public class CountryController {
    @Autowired
    ICountryService iCountryService;
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountry() {
        List<Country> CountryList = iCountryService.getAll();
        if (CountryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(CountryList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody Country Country) {
        return new ResponseEntity<>(iCountryService.save(Country), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable int id) {
        Country currentCountry = iCountryService.findById(id);
        if (currentCountry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentCountry, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> editCountry(@RequestBody Country Country, @PathVariable int id) {
        Country currentCountry = iCountryService.findById(id);
        if (currentCountry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCountry.setCountryName(Country.getCountryName());
        return new ResponseEntity<>(iCountryService.edit(currentCountry), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable int id) {
        if (iCountryService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCountryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
