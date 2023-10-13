package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.IImageService;
import com.example.module4_shoesshop.model.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    IImageService iImageService;
    @GetMapping
    public ResponseEntity<List<Images>> getAllImages() {
        List<Images> ImagesList = iImageService.getAll();
        if (ImagesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ImagesList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Images> addImages(@RequestBody Images Images) {
        return new ResponseEntity<>(iImageService.save(Images), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Images> getImages(@PathVariable int id) {
        Images currentImages = iImageService.findById(id);
        if (currentImages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentImages, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Images> editImages(@RequestBody Images Images, @PathVariable int id) {
        Images currentImages = iImageService.findById(id);
        if (currentImages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentImages.setImg(Images.getImg());
        currentImages.setProduct(Images.getProduct());
        return new ResponseEntity<>(iImageService.edit(currentImages), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Images> deleteImages(@PathVariable int id) {
        if (iImageService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
