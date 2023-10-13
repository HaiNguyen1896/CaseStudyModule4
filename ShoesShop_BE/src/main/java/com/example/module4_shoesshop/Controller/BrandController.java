package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.IBrandService;
import com.example.module4_shoesshop.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    IBrandService iBrandService;
    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrand() {
        List<Brand> brandList = iBrandService.getAll();
        if (brandList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brandList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Brand> addBrand(@RequestBody Brand Brand) {
        return new ResponseEntity<>(iBrandService.save(Brand), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable int id) {
        Brand currentBrand = iBrandService.findById(id);
        if (currentBrand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentBrand, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Brand> editBrand(@RequestBody Brand Brand, @PathVariable int id) {
        Brand currentBrand = iBrandService.findById(id);
        if (currentBrand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentBrand.setBrandName(Brand.getBrandName());
        return new ResponseEntity<>(iBrandService.edit(currentBrand), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable int id) {
        if (iBrandService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iBrandService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
