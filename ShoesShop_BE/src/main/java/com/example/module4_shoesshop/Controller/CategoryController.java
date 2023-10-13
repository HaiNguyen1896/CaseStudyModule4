package com.example.module4_shoesshop.Controller;
import com.example.module4_shoesshop.Service.IService.ICategoryService;
import com.example.module4_shoesshop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = iCategoryService.getAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(iCategoryService.save(category), HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        Category currentCategory = iCategoryService.findById(id);
        if (currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currentCategory, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(@RequestBody Category category, @PathVariable int id) {
        Category currentCategory = iCategoryService.findById(id);
        if (currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setName(category.getName());
        return new ResponseEntity<>(iCategoryService.edit(currentCategory), HttpStatus.OK);
    }



    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        if (iCategoryService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
