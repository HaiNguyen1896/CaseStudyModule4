package com.example.module4_shoesshop.Service.ServiceImpl;

import com.example.module4_shoesshop.Repository.IProductRepo;
import com.example.module4_shoesshop.Service.IService.IProductService;
import com.example.module4_shoesshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepo iProductRepo;

    @Override
    public Product save(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public Product edit(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public void delete(long id) {
        iProductRepo.deleteById(id);
    }

    @Override
    public Product findById(long id) {
        return iProductRepo.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) iProductRepo.findAll();
    }

    @Override
    public int findByCateID(long id) {
        return iProductRepo.findByCateID(id);
    }

    @Override
    public List<Product> findProductByPriceRange(int r1, int r2) {
        return iProductRepo.findProductByPriceRange(r1,r2);
    }

    @Override
    public List<Product> findProductBySizeID(long id) {
        return iProductRepo.findProductBySizeID(id);
    }

    @Override
    public List<Product> findProductByCategoryID(long id) {
        return iProductRepo.findProductCategoryID(id);
    }

    @Override
    public List<Product> findProduct(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page,limit);
        Page<Product>productPage = iProductRepo.findAll(pageableRequest);
        List<Product>productList = productPage.getContent();
        return productList;
    }

}
