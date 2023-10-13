package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.ISizeService;
import com.example.module4_shoesshop.model.Product;
import com.example.module4_shoesshop.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sizes")
public class SizeController {
    @Autowired
    ISizeService iSizeService;
    
    @GetMapping
    public ResponseEntity<List<Size>> getAllSizes() {
        List<Size> SizesList = iSizeService.getAll();
        if (SizesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SizesList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Size> addSizes(@RequestBody Size Sizes) {
        return new ResponseEntity<>(iSizeService.save(Sizes), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Size> getSizes(@PathVariable long id) {
        Size currentSizes = iSizeService.findById(id);
        if (currentSizes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentSizes, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Size> editSizes(@RequestBody Size Sizes, @PathVariable long id) {
        Size currentSizes = iSizeService.findById(id);
        if (currentSizes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentSizes.setSize(Sizes.getSize());
        return new ResponseEntity<>(iSizeService.edit(currentSizes), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Size> deleteSizes(@PathVariable long id) {
        if (iSizeService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iSizeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findByProductID/{id}")
    List<Size>findSizesByProductId(@PathVariable long id) {
        List<Size> sizesList = iSizeService.findSizesByProductId(id);
        System.out.println(sizesList);
        return sizesList;
    }
}
